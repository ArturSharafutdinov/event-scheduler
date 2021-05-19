package ru.ivanov.evgeny.eventscheduler.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

public interface SimpleDao {

    <T> T findById(Class<T> entityClass, Serializable id);

    <T> Serializable save(T entity);

    <T> void saveOrUpdate(T entity);

    <T> void update(T entity);

    <T> void delete(T entity);

    void flush();

    <T> List<T> findAll(Class<T> entityClass);
}
