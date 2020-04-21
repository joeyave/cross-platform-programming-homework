package edu.dnu.fpm.pz.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    // CREATE.
    void insert(T entity) throws SQLException;

    void insert(List<T> entities) throws SQLException;

    // READ.
    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    // UPDATE.
    void update(T entity) throws SQLException;

    void update(List<T> entities) throws SQLException;

    // DELETE.
    boolean deleteById(int id) throws SQLException;
}
