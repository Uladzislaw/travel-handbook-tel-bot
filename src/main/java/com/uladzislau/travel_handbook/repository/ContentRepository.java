package com.uladzislau.travel_handbook.repository;

import com.uladzislau.travel_handbook.model.Content;

import java.util.List;

public interface ContentRepository extends BaseRepository<Content, Long> {
    List<Content> findAllByCityName(String cityName);
}
