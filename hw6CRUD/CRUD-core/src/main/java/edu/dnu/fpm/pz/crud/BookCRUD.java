package edu.dnu.fpm.pz.crud;

import edu.dnu.fpm.pz.crud.interfaces.ICRUD;
import edu.dnu.fpm.pz.entities.BookEntity;

import java.util.List;

public class BookCRUD implements ICRUD<BookEntity> {
    @Override
    public void create(BookEntity entity) {

    }

    @Override
    public void create(List<BookEntity> entities) {

    }

    @Override
    public List<BookEntity> read() {
        return null;
    }

    @Override
    public void update(BookEntity entity) {

    }

    @Override
    public void update(List<BookEntity> entities) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
