package com.uladzislau.travel_handbook.service.city;

import com.uladzislau.travel_handbook.model.City;
import com.uladzislau.travel_handbook.repository.BaseRepository;
import com.uladzislau.travel_handbook.repository.CityRepository;
import com.uladzislau.travel_handbook.service.BaseService;
import com.uladzislau.travel_handbook.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class CityServiceImpl extends BaseService<City> implements Service<City> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    protected BaseRepository<City, Long> relatedRepository() {
        return cityRepository;
    }
}
