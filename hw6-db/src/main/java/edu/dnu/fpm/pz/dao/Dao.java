package edu.dnu.fpm.pz.dao;

import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    // CREATE.
    void insert(T entity) throws SQLException, NoSuchObjectException;

    void insert(List<T> entities) throws SQLException, NoSuchObjectException;

    // READ.
    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    // UPDATE.
    void update(T entity) throws SQLException, NoSuchObjectException;

    void update(List<T> entities) throws SQLException, NoSuchObjectException;

    // DELETE.
    boolean deleteById(int id) throws SQLException;
}
