--create database cities_travel_handbook

create table cities
(
    id   serial,
    name varchar(70) not null unique,
    CONSTRAINT "cities_pk" primary key (id)
);

create table content
(
    id      serial,
    text    varchar(255) not null,
    city_id integer      not null,
    CONSTRAINT "content_pk" primary key (id)
);

ALTER TABLE "content"
    ADD CONSTRAINT "content_fk0" FOREIGN KEY (city_id) REFERENCES cities (id);
