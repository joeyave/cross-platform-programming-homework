package edu.dnu.fpm.pz.crud;

import edu.dnu.fpm.pz.crud.interfaces.ICRUD;
import edu.dnu.fpm.pz.entities.UserEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserCRUD implements ICRUD<UserEntity> {

    @Override
    public void create(UserEntity entity) throws IOException, SQLException {
    }

    @Override
    public void create(List<UserEntity> entities) {

    }

    @Override
    public List<UserEntity> read() {
        return null;
    }

    @Override
    public void update(UserEntity entity) {

    }

    @Override
    public void update(List<UserEntity> entities) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
