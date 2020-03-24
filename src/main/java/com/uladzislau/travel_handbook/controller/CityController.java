package com.uladzislau.travel_handbook.controller;

import com.uladzislau.travel_handbook.dto.CityDto;
import com.uladzislau.travel_handbook.service.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/city")
public class CityController {

    private final CityService cityService;

    @GetMapping(value = "{id}")
    public CityDto getOne(@PathVariable long id) {
        return cityService.readById(id);
    }
}
