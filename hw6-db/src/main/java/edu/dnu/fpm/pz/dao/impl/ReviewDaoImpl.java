package edu.dnu.fpm.pz.dao.impl;

import edu.dnu.fpm.pz.config.ServiceProviderConfig;
import edu.dnu.fpm.pz.dao.Dao;
import edu.dnu.fpm.pz.entity.EntityValidator;
import edu.dnu.fpm.pz.entity.ReviewEntity;

import java.rmi.NoSuchObjectException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReviewDaoImpl implements Dao<ReviewEntity> {
    EntityValidator validator = new EntityValidator();

    @Override
    public void insert(ReviewEntity reviewEntity) throws SQLException, NoSuchObjectException {
        validator.reviewEntityValidate(reviewEntity);
        try (Connection connection = ServiceProviderConfig.getConnection()) {

            // Prepare statement to execute.
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(
                    "insert into reviews (review_user_id, review_book_id, review, rating) " +
                            "values (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, reviewEntity.getUserId());
            preparedStatement.setInt(2, reviewEntity.getBookId());
            preparedStatement.setString(3, reviewEntity.getReview());
            preparedStatement.setInt(4, reviewEntity.getRating());

            // Execute statement.
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void insert(List<ReviewEntity> entities) throws SQLException, NoSuchObjectException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {

            // Save autocommit configs and disable it.
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into reviews (review_user_id, review_book_id, review, rating) " +
                            "values (?, ?, ?, ?)"
            );

            for (var reviewEntity : entities) {
                validator.reviewEntityValidate(reviewEntity);
                preparedStatement.setInt(1, reviewEntity.getUserId());
                preparedStatement.setInt(2, reviewEntity.getBookId());
                preparedStatement.setString(3, reviewEntity.getReview());
                preparedStatement.setInt(4, reviewEntity.getRating());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autocommit);
        }
    }

    @Override
    public LinkedList<ReviewEntity> getAll() throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection();
             Statement statement = connection.createStatement()) {
            LinkedList<ReviewEntity> reviewEntities = new LinkedList<>();

            try (ResultSet resultSet = statement.executeQuery(
                    "select * from reviews"
            )) {
                while (resultSet.next()) {
                    ReviewEntity reviewEntity = new ReviewEntity(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    );
                    reviewEntities.add(reviewEntity);
                }

                return reviewEntities;
            }
        }
    }

    @Override
    public ReviewEntity getById(int id) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from reviews " +
                            "where review_id = ?"
            );
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new ReviewEntity(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    );
                }

                return null;
            }
        }
    }

    public LinkedList<ReviewEntity> getByUserId(int id) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from reviews " +
                            "where review_user_id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            LinkedList<ReviewEntity> reviewEntities = new LinkedList<>();

            while (resultSet.next()) {
                ReviewEntity reviewEntity = new ReviewEntity(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
                reviewEntities.add(reviewEntity);
            }

            return reviewEntities;
        }
    }

    public LinkedList<ReviewEntity> getByBookId(int id) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from reviews " +
                            "where review_book_id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            LinkedList<ReviewEntity> reviewEntities = new LinkedList<>();

            while (resultSet.next()) {
                ReviewEntity reviewEntity = new ReviewEntity(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
                reviewEntities.add(reviewEntity);
            }

            return reviewEntities;
        }
    }

    @Override
    public void update(ReviewEntity reviewEntity) throws SQLException, NoSuchObjectException {
        validator.reviewEntityValidate(reviewEntity);
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update reviews " +
                            "set review = ?, rating = ? " +
                            "where review_id = ?"
            );

            preparedStatement.setString(1, reviewEntity.getReview());
            preparedStatement.setInt(2, reviewEntity.getRating());
            preparedStatement.setInt(3, reviewEntity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(List<ReviewEntity> entities) throws SQLException, NoSuchObjectException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update reviews " +
                            "set review_user_id = ?, review_book_id = ?, review = ?, rating = ? " +
                            "where review_id = ?"
            );

            for (var reviewEntity : entities) {
                validator.reviewEntityValidate(reviewEntity);
                preparedStatement.setInt(1, reviewEntity.getUserId());
                preparedStatement.setInt(2, reviewEntity.getBookId());
                preparedStatement.setString(3, reviewEntity.getReview());
                preparedStatement.setInt(4, reviewEntity.getRating());
                preparedStatement.setInt(5, reviewEntity.getId());

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
                    "delete from reviews where review_id = ?"
            );

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        }
    }

    public void deleteAll() throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from reviews"
            );

            preparedStatement.executeUpdate();
        }
    }
}
