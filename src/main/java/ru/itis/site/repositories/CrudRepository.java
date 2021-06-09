package ru.itis.site.repositories;

import java.util.List;

public interface CrudRepository<T, ID> {

    List<T> findAll();

    void save(T entity);
}
