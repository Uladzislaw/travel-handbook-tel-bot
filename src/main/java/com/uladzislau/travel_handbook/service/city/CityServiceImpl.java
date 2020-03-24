package com.uladzislau.travel_handbook.service.city;

import com.uladzislau.travel_handbook.dto.CityDto;
import com.uladzislau.travel_handbook.exception.ResourceNotFoundException;
import com.uladzislau.travel_handbook.model.City;
import com.uladzislau.travel_handbook.repository.BaseRepository;
import com.uladzislau.travel_handbook.repository.CityRepository;
import com.uladzislau.travel_handbook.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl extends BaseService<City> implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    protected BaseRepository<City, Long> relatedRepository() {
        return cityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CityDto readById(long id) {
        City city = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " wasn't found."));

        return CityDto.builder()
                .name(city.getName())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDto> readAll() {
        return findAll().stream()
                .map(city -> CityDto.builder().name(city.getName()).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(CityDto city) {
        City objectToPersist = City.builder()
                .name(city.getName())
                .build();
        cityRepository.save(objectToPersist);
    }
}
