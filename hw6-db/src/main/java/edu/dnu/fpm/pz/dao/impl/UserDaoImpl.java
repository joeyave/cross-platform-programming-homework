package edu.dnu.fpm.pz.dao.impl;


import edu.dnu.fpm.pz.dao.Dao;
import edu.dnu.fpm.pz.config.ServiceProviderConfig;
import edu.dnu.fpm.pz.entity.UserEntity;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements Dao<UserEntity> {

    @Override
    public void create(UserEntity entity) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (user_name, user_password) " +
                            "values (?, ?)"
            );

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void create(List<UserEntity> entities) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(autocommit);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users (user_name, user_password) " +
                            "values (?, ?)"
            );

            for (var entity : entities) {
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getPassword());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autocommit);
        }
    }

    @Override
    public List<UserEntity> read() throws IOException, SQLException {
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
    public void update(UserEntity entity) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update users " +
                            "set user_name = ?, user_password = ? " +
                            "where user_id = ?"
            );

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setInt(3, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(List<UserEntity> entities) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update users " +
                            "set user_name = ?, user_password = ? " +
                            "where user_id = ?"
            );

            for (var entity : entities) {
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getPassword());
                preparedStatement.setInt(3, entity.getId());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autocommit);
        }
    }

    @Override
    public boolean delete(int id) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from users " +
                            "where user_id = ?"
            );

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        }
    }
}
