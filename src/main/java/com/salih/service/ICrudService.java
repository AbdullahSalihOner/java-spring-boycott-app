package com.salih.service;

import java.util.List;

public interface ICrudService<T, ID> {
    List<T> getAll();
    T getById(ID id);
    T add(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}