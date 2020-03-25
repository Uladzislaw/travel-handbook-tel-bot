package com.uladzislau.travel_handbook.controller;

import com.uladzislau.travel_handbook.dto.CityDto;
import com.uladzislau.travel_handbook.service.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/city")
public class CityController {

    private final CityService cityService;

    @GetMapping(value = "{id}")
    public CityDto getOne(@PathVariable long id) {
        return cityService.readById(id);
    }

    @GetMapping(value = "/all")
    public List<CityDto> getAll() {
        return cityService.readAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCity(@RequestBody @Valid CityDto city) {
        cityService.create(city);
    }

    @PutMapping(value = "{id}")
    public CityDto update(@PathVariable long id,
                          @RequestBody @Valid CityDto city) {
        return cityService.update(id, city);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        cityService.delete(id);
    }
}
