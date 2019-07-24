package com.time.tracking.dao;

import java.util.List;

public interface GenericDao<T> {

    void insert(T entity);

    T findById(int id);

    List<T> findAll();

}
