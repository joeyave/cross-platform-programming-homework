package edu.dnu.fpm.pz.crud.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 */
public interface ICRUD<T> {
    void create(T entity) throws IOException, SQLException;

    void create(List<T> entities);

    List<T> read();

    void update(T entity);

    void update(List<T> entities);

    boolean delete(int id);
}
