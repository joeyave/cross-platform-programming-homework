package edu.dnu.fpm.pz.dao.impl;

import edu.dnu.fpm.pz.config.ServiceProviderConfig;
import edu.dnu.fpm.pz.dao.Dao;
import edu.dnu.fpm.pz.entity.BookEntity;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookDaoImpl implements Dao<BookEntity> {
    @Override
    public void create(BookEntity entity) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {

            // Prepare statement to execute.
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(
                    "insert into books (book_isbn, book_title, book_author, book_year) " +
                            "values (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, entity.getIsbn());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getAuthor());
            preparedStatement.setInt(4, entity.getYear());

            // Execute statement.
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void create(List<BookEntity> entities) throws SQLException, IOException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {

            // Save autocommit configs and disable it.
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into books (book_isbn, book_title, book_author, book_year) " +
                            "values (?, ?, ?, ?)"
            );

            for (BookEntity entity : entities) {
                preparedStatement.setInt(1, entity.getIsbn());
                preparedStatement.setString(2, entity.getTitle());
                preparedStatement.setString(3, entity.getAuthor());
                preparedStatement.setInt(4, entity.getYear());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autocommit);
        }
    }

    @Override
    public List<BookEntity> read() throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection();
             Statement statement = connection.createStatement()) {
            List<BookEntity> bookEntities = new LinkedList<>();

            try (ResultSet resultSet = statement.executeQuery(
                    "select * from books"
            )) {
                while (resultSet.next()) {
                    BookEntity bookEntity = new BookEntity(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    );
                    bookEntities.add(bookEntity);
                }

                return bookEntities;
            }
        }
    }

    @Override
    public void update(BookEntity entity) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update books " +
                            "set book_isbn = ?, book_title = ?, book_author = ?, book_year = ?)" +
                            "where book_id = ?"
            );

            preparedStatement.setInt(1, entity.getIsbn());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getAuthor());
            preparedStatement.setInt(4, entity.getYear());
            preparedStatement.setInt(5, entity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(List<BookEntity> entities) throws IOException, SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update books " +
                            "set book_isbn = ?, book_title = ?, book_author = ?, book_year = ?" +
                            "where book_id = ?"
            );

            for (BookEntity entity : entities) {
                preparedStatement.setInt(1, entity.getIsbn());
                preparedStatement.setString(2, entity.getTitle());
                preparedStatement.setString(3, entity.getAuthor());
                preparedStatement.setInt(4, entity.getYear());
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
                    "delete from books " +
                            "where book_id = ?"
            );

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        }
    }
}
