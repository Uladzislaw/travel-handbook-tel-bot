--create database cities_travel_handbook

create table cities
(
    id   serial,
    name varchar(70) not null unique,
    CONSTRAINT "cities_pk" primary key (id)
);

create table content
(
    id   serial,
    text varchar(255) not null,
    CONSTRAINT "content_pk" primary key (id)
);

create table city_info
(
    id         serial,
    city_id    integer not null,
    content_id integer not null,
    CONSTRAINT "city_info_pk" primary key (id)
);

ALTER TABLE "city_info"
    ADD CONSTRAINT "city_info_fk0" FOREIGN KEY (city_id) REFERENCES cities (id);
ALTER TABLE "city_info"
    ADD CONSTRAINT "city_info_fk1" FOREIGN KEY (content_id) REFERENCES content (id);
