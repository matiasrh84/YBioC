package com.ybc.ybioq.controller;

import com.ybc.ybioq.service.CrudService;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudController<T, ID> implements CrudController<T, ID> {

    private final CrudService<T, ID> service;

    protected AbstractCrudController(CrudService<T, ID> service) {
        this.service = service;
    }

    @Override
    public List<T> findAll() {
        return service.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return service.findById(id);
    }

    @Override
    public T save(T entity) {
        return service.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        service.deleteById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return service.existsById(id);
    }

    @Override
    public long count() {
        return service.count();
    }
}
