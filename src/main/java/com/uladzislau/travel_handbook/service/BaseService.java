package com.uladzislau.travel_handbook.service;

import com.uladzislau.travel_handbook.exception.ResourceNotFoundException;
import com.uladzislau.travel_handbook.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> implements Service<T> {

    protected abstract BaseRepository<T, Long> relatedRepository();

    @Override
    public List<T> findAll() {
        return relatedRepository().findAll();
    }

    @Override
    public Optional<T> findById(Long id) {
        return relatedRepository().findById(id);
    }

    @Override
    public void save(T object) {
        relatedRepository().save(object);
    }

    @Override
    public void delete(T object) {
        relatedRepository().delete(object);
    }

    public T findByIdOrElseThrow(long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity with id " + id + " wasn't found."));
    }
}
