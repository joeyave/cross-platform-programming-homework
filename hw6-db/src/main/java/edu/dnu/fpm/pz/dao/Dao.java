package edu.dnu.fpm.pz.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    void create(T entity) throws IOException, SQLException;

    void create(List<T> entities) throws SQLException, IOException;

    List<T> read() throws IOException, SQLException;

    void update(T entity) throws IOException, SQLException;

    void update(List<T> entities) throws IOException, SQLException;

    boolean delete(int id) throws IOException, SQLException;
}
