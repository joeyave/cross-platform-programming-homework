package edu.dnu.fpm.pz.dao.impl;

import edu.dnu.fpm.pz.config.ServiceProviderConfig;
import edu.dnu.fpm.pz.dao.Dao;
import edu.dnu.fpm.pz.entity.ReviewEntity;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReviewDaoImpl implements Dao<ReviewEntity> {
    @Override
    public void create(ReviewEntity entity) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {

            // Prepare statement to execute.
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(
                    "insert into reviews (review_user_id, review_book_id, review, rating) " +
                            "values (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setString(3, entity.getReview());
            preparedStatement.setInt(4, entity.getRating());

            // Execute statement.
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void create(List<ReviewEntity> entities) throws SQLException, IOException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {

            // Save autocommit configs and disable it.
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into reviews (review_user_id, review_book_id, review, rating) " +
                            "values (?, ?, ?, ?)"
            );

            for (ReviewEntity entity : entities) {
                preparedStatement.setInt(1, entity.getUserId());
                preparedStatement.setInt(2, entity.getBookId());
                preparedStatement.setString(3, entity.getReview());
                preparedStatement.setInt(4, entity.getRating());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autocommit);
        }
    }

    @Override
    public List<ReviewEntity> read() throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection();
             Statement statement = connection.createStatement()) {
            List<ReviewEntity> reviewEntities = new LinkedList<>();

            try (ResultSet resultSet = statement.executeQuery(
                    "select * from books"
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
    public void update(ReviewEntity entity) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update reviews " +
                            "set review_user_id = ?, review_book_id = ?, review = ?, rating = ? " +
                            "where review_id = ?"
            );

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setString(3, entity.getReview());
            preparedStatement.setInt(4, entity.getRating());
            preparedStatement.setInt(5, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(List<ReviewEntity> entities) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update reviews " +
                            "set review_user_id = ?, review_book_id = ?, review = ?, rating = ? " +
                            "where review_id = ?"
            );

            for (ReviewEntity entity : entities) {
                preparedStatement.setInt(1, entity.getUserId());
                preparedStatement.setInt(2, entity.getBookId());
                preparedStatement.setString(3, entity.getReview());
                preparedStatement.setInt(4, entity.getRating());
                preparedStatement.setInt(5, entity.getId());

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
                    "delete from reviews where review_id = ?"
            );

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        }
    }
}
