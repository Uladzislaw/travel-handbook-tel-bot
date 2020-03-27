package com.uladzislau.travel_handbook.service.content;

import com.uladzislau.travel_handbook.dto.ContentDto;
import com.uladzislau.travel_handbook.model.Content;
import com.uladzislau.travel_handbook.service.Service;

import java.util.List;

public interface ContentService extends Service<Content> {
    List<ContentDto> readByCityId(long cityId);

    List<ContentDto> readAllByCityName(String cityName);

    void create(ContentDto content);

    ContentDto update(long id, ContentDto content);

    void delete(long id);

    ContentDto readOneByCityName(String city);
}
