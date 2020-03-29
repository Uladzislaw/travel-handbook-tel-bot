package com.uladzislau.travel_handbook.repository;

import com.uladzislau.travel_handbook.config.TestConfiguration;
import com.uladzislau.travel_handbook.model.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestConfiguration.class)
@ActiveProfiles("test")
@Rollback
@Transactional
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void findById() {
        assertEquals("Берлин", cityRepository.findById(1L).get().getName());
    }

    @Test
    public void findAll() {
        assertEquals(10, cityRepository.findAll().size());
    }

    @Test
    public void save() {
        String name = "Вильнюс";
        City toSave = City.builder().name(name).build();
        cityRepository.save(toSave);
        assertTrue(cityRepository.findAll().contains(toSave));
    }

    @Test
    public void delete() {
        cityRepository.delete(cityRepository.getOne(1L));
        assertFalse(cityRepository.findById(1L).isPresent());
    }
}