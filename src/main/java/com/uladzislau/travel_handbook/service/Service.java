package com.uladzislau.travel_handbook.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    Optional<T> findById(Long id);

    void save(T object);

    void delete(T object);

    List<T> findAll();
}
