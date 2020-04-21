package edu.dnu.fpm.pz.dao.impl;

import edu.dnu.fpm.pz.config.ServiceProviderConfig;
import edu.dnu.fpm.pz.dao.Dao;
import edu.dnu.fpm.pz.entity.BookEntity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookDaoImpl implements Dao<BookEntity> {
    @Override
    public void insert(BookEntity bookEntity) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {

            // Prepare statement to execute.
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(
                    "insert into books (book_isbn, book_title, book_author, book_year) " +
                            "values (?, ?, ?, ?)"
            );
            preparedStatement.setString(1, bookEntity.getIsbn());
            preparedStatement.setString(2, bookEntity.getTitle());
            preparedStatement.setString(3, bookEntity.getAuthor());
            preparedStatement.setInt(4, bookEntity.getYear());

            // Execute statement.
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void insert(List<BookEntity> bookEntities) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {

            // Save autocommit configs and disable it.
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into books (book_isbn, book_title, book_author, book_year) " +
                            "values (?, ?, ?, ?)"
            );

            for (var bookEntity : bookEntities) {
                preparedStatement.setString(1, bookEntity.getIsbn());
                preparedStatement.setString(2, bookEntity.getTitle());
                preparedStatement.setString(3, bookEntity.getAuthor());
                preparedStatement.setInt(4, bookEntity.getYear());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(autocommit);
        }
    }

    @Override
    public List<BookEntity> getAll() throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection();
             Statement statement = connection.createStatement()) {
            List<BookEntity> bookEntities = new LinkedList<>();

            try (ResultSet resultSet = statement.executeQuery(
                    "select * from books"
            )) {
                while (resultSet.next()) {
                    BookEntity bookEntity = new BookEntity(
                            resultSet.getInt(1),
                            resultSet.getString(2),
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
    public BookEntity getById(int id) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from books " +
                            "where book_id = ?"
            );
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new BookEntity(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    );
                }

                return null;
            }
        }
    }

    public List<BookEntity> getByQuery(String query) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * " +
                            "from books " +
                            "where lower(book_isbn) like lower(?) " +
                            "or lower (book_title) like lower(?) " +
                            "or lower (book_author) like lower(?)"
            );
            preparedStatement.setString(1, query);
            preparedStatement.setString(2, query);
            preparedStatement.setString(3, query);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<BookEntity> bookEntities = new LinkedList<>();
            while (resultSet.next()) {
                BookEntity bookEntity = new BookEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
                bookEntities.add(bookEntity);
            }

            return bookEntities;
        }
    }


    @Override
    public void update(BookEntity bookEntity) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update books " +
                            "set book_isbn = ?, book_title = ?, book_author = ?, book_year = ?)" +
                            "where book_id = ?"
            );

            preparedStatement.setString(1, bookEntity.getIsbn());
            preparedStatement.setString(2, bookEntity.getTitle());
            preparedStatement.setString(3, bookEntity.getAuthor());
            preparedStatement.setInt(4, bookEntity.getYear());
            preparedStatement.setInt(5, bookEntity.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(List<BookEntity> bookEntities) throws SQLException {
        try (Connection connection = ServiceProviderConfig.getConnection()) {
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update books " +
                            "set book_isbn = ?, book_title = ?, book_author = ?, book_year = ?" +
                            "where book_id = ?"
            );

            for (var bookEntity : bookEntities) {
                preparedStatement.setString(1, bookEntity.getIsbn());
                preparedStatement.setString(2, bookEntity.getTitle());
                preparedStatement.setString(3, bookEntity.getAuthor());
                preparedStatement.setInt(4, bookEntity.getYear());
                preparedStatement.setInt(5, bookEntity.getId());

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
                    "delete from books " +
                            "where book_id = ?"
            );

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        }
    }
}
