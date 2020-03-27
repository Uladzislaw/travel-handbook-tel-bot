package com.uladzislau.travel_handbook.controller;

import com.uladzislau.travel_handbook.dto.ContentDto;
import com.uladzislau.travel_handbook.service.content.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/content")
public class ContentController {

    private final ContentService contentService;

    @GetMapping(value = "/{cityId}")
    public List<ContentDto> getAllByCityId(@PathVariable long cityId) {
        return contentService.readByCityId(cityId);
    }

    @GetMapping
    public List<ContentDto> getAllByCityName(@RequestParam("city") String cityName) {
        return contentService.readAllByCityName(cityName);
    }

    @GetMapping(value = "/one/{city}")
    public ContentDto getOneByCityName(@PathVariable String city) {
        return contentService.readOneByCityName(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ContentDto content) {
        contentService.create(content);
    }

    @PutMapping(value = "{id}")
    public ContentDto update(@PathVariable long id,
                             @RequestBody @Valid ContentDto content) {
        return contentService.update(id, content);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        contentService.delete(id);
    }
}
