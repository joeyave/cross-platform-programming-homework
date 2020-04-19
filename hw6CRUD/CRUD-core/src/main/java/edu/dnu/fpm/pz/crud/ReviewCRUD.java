package edu.dnu.fpm.pz.crud;

import edu.dnu.fpm.pz.crud.interfaces.ICRUD;
import edu.dnu.fpm.pz.entities.ReviewEntity;

import java.util.List;

public class ReviewCRUD implements ICRUD<ReviewEntity> {
    @Override
    public void create(ReviewEntity entity) {

    }

    @Override
    public void create(List<ReviewEntity> entities) {

    }

    @Override
    public List<ReviewEntity> read() {
        return null;
    }

    @Override
    public void update(ReviewEntity entity) {

    }

    @Override
    public void update(List<ReviewEntity> entities) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
