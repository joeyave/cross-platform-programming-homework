package edu.dnu.fpm.pz.dao.impl;


import edu.dnu.fpm.pz.config.ServiceProviderConfig;
import edu.dnu.fpm.pz.dao.Dao;
import edu.dnu.fpm.pz.entity.EntityValidator;
import edu.dnu.fpm.pz.entity.UserEntity;

import java.rmi.NoSuchObjectException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements Dao<UserEntity> {
    EntityValidator validator = new EntityValidator();

    @Override
    public void insert(UserEntity userEntity) throws SQLException, NoSuchObjectException {
        validator.userEntityValidate(userEntity);
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (user_name, user_password) " +
                            "values (?, ?)"
            );

            preparedStatement.setString(1, userEntity.getName());
            preparedStatement.setString(2, userEntity.getPassword());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void insert(List<UserEntity> entities) throws SQLException, NoSuchObjectException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (user_name, user_password) " +
                            "values (?, ?)"
            );

            for (var userEntity : entities) {
                validator.userEntityValidate(userEntity);
                preparedStatement.setString(1, userEntity.getName());
                preparedStatement.setString(2, userEntity.getPassword());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autocommit);
        }
    }

    @Override
    public LinkedList<UserEntity> getAll() throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection();
             Statement statement = connection.createStatement()) {

            LinkedList<UserEntity> userEntities = new LinkedList<>();
            try (ResultSet resultSet = statement.executeQuery(
                    "select * from users"
            )) {
                while (resultSet.next()) {
                    UserEntity userEntity = new UserEntity(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    );
                    userEntities.add(userEntity);
                }
            }

            return userEntities;
        }
    }

    @Override
    public UserEntity getById(int id) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from users " +
                            "where user_id = ?"
            );
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new UserEntity(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    );
                }

                return null;
            }
        }
    }

    public UserEntity getByUsername(String username) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from users " +
                            "where user_name = ?"
            );
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new UserEntity(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    );
                }

                return null;
            }
        }
    }

    @Override
    public void update(UserEntity userEntity) throws SQLException, NoSuchObjectException {
        validator.userEntityValidate(userEntity);
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update users " +
                            "set user_name = ?, user_password = ? " +
                            "where user_id = ?"
            );

            preparedStatement.setString(1, userEntity.getName());
            preparedStatement.setString(2, userEntity.getPassword());
            preparedStatement.setInt(3, userEntity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(List<UserEntity> entities) throws SQLException, NoSuchObjectException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update users " +
                            "set user_name = ?, user_password = ? " +
                            "where user_id = ?"
            );

            for (var userEntity : entities) {
                validator.userEntityValidate(userEntity);
                preparedStatement.setString(1, userEntity.getName());
                preparedStatement.setString(2, userEntity.getPassword());
                preparedStatement.setInt(3, userEntity.getId());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autocommit);
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from users " +
                            "where user_id = ?"
            );

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        }
    }

    public void deleteAll() throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from users"
            );

            preparedStatement.executeUpdate();
        }
    }
}
