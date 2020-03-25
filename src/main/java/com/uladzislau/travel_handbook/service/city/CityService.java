package com.uladzislau.travel_handbook.service.city;

import com.uladzislau.travel_handbook.dto.CityDto;
import com.uladzislau.travel_handbook.model.City;
import com.uladzislau.travel_handbook.service.Service;

import java.util.List;

public interface CityService extends Service<City> {

    CityDto readById(long id);

    List<CityDto> readAll();

    void create(CityDto city);

    CityDto update(long id, CityDto city);

    void delete(long id);
}
