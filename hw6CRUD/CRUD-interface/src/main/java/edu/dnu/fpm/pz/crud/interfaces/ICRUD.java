package edu.dnu.fpm.pz.crud.interfaces;

import java.util.List;

/**
 * Hello world!
 */
public interface ICRUD<T> {
    void create(T entity);

    void create(List<T> entities);

    List<T> read();

    void update(T entity);

    void update(List<T> entities);

    boolean delete(int id);
}
