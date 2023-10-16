package com.leduy.backend.service;

public interface BaseService<T,K,R> {
    T save(R request);

    T update(R request);

    T findById(K id);

    boolean delete(K id);
}
