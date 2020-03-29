package com.uladzislau.travel_handbook.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EntityScan(basePackages = "com.uladzislau.*")
@ComponentScan(basePackages = "com.uladzislau.*")
@EnableJpaRepositories(basePackages = "com.uladzislau.*")
@EnableTransactionManagement
@Profile("test")
public class TestConfiguration {

    private static final String INIT_SCHEMA = "/test/db_schema.sql";
    private static final String FILL_TABLES = "/test/fill_table.sql";

    @Autowired
    private Environment environment;

    @Bean
    @Profile("test")
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(environment.getProperty("datasource.driver-class-name"))
                .url(environment.getProperty("datasource.url"))
                .username(environment.getProperty("datasource.username"))
                .password(environment.getProperty("datasource.password"))
                .build();

        Resource initSchema = new ClassPathResource(INIT_SCHEMA);
        Resource fillTables = new ClassPathResource(FILL_TABLES);

        DatabasePopulator databasePopulator1 = new ResourceDatabasePopulator(initSchema);
        DatabasePopulator databasePopulator2 = new ResourceDatabasePopulator(fillTables);

        DatabasePopulatorUtils.execute(databasePopulator1, dataSource);
        DatabasePopulatorUtils.execute(databasePopulator2, dataSource);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.uladzislau");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @SneakyThrows
    private Properties additionalProperties() {
        Properties additionalProps = new Properties();
        additionalProps.load(new ClassPathResource("application-test.properties").getInputStream());
        return additionalProps;
    }
}
