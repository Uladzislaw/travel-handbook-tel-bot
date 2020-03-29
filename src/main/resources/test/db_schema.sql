--create database cities_travel_handbook

create table cities
(
    id   bigint      not null auto_increment,
    name varchar(70) not null unique,
    primary key (id)
);

create table content
(
    id      bigint        not null auto_increment,
    text    varchar(1000) not null,
    city_id bigint        not null,
    primary key (id),
    foreign key (city_id) references cities (id)
);

CREATE SEQUENCE cities_id_seq START WITH 15 INCREMENT BY 3;
CREATE SEQUENCE content_id_seq START WITH 50 INCREMENT BY 10;
