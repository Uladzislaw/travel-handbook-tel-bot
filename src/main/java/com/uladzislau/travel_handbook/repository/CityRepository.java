package com.uladzislau.travel_handbook.repository;

import com.uladzislau.travel_handbook.model.City;

import java.util.List;

public interface CityRepository extends BaseRepository<City, Long> {
    List<City> findByName(String name);
}
