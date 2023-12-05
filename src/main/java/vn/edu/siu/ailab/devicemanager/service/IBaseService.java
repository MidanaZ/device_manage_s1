package vn.edu.siu.ailab.devicemanager.service;

import java.util.Optional;

public interface IBaseService<T> {
    Iterable<T> findAll();
    Optional<T> findById(int id);
    T save(T t);
    void delete(int id);
}
