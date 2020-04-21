package edu.dnu.fpm.pz;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.dnu.fpm.pz.dao.impl.BookDaoImpl;
import edu.dnu.fpm.pz.dao.impl.ReviewDaoImpl;
import edu.dnu.fpm.pz.dao.impl.UserDaoImpl;
import edu.dnu.fpm.pz.entity.BookEntity;
import edu.dnu.fpm.pz.entity.ReviewEntity;
import edu.dnu.fpm.pz.entity.UserEntity;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Hw6DbApp {
    public static void main(String[] args) {
        var books = readCsvFile("src/main/resources/books.csv");
        fillBooksTable(books);

        register("user4", "user4");
        var loggedInUser = login("user4", "user4");
        if (Objects.nonNull(loggedInUser)) {
            var queriedBooks = searchBookByQuery("dark");

            System.out.println("Adding review.");
            leaveReview("don't like it!", 3, loggedInUser, queriedBooks.get(0));
            printReviews();

            System.out.println("Updating review.");
            System.out.println("User reviews before update.");
            printReviews(loggedInUser);
            var userReviews = getUserReviews(loggedInUser);
            updateUserReview("updated review", 3, userReviews.get(0));
            System.out.println("User reviews after update.");
            printReviews(loggedInUser);

            System.out.println("Reviews before delete.");
            printReviews();
            System.out.println("Reviews before delete.");
            removeReview(userReviews.get(2));
            printReviews();
        }
    }

    public static UserEntity register(String username, String password) {
        UserDaoImpl userDao = new UserDaoImpl();
        UserEntity newUser = new UserEntity();

        try {
            var existingUser = userDao.getByUsername(username);

            if (Objects.nonNull(existingUser)) {
                System.out.println("Username is taken!");
            }
            userDao.insert(new UserEntity(username, password));
            newUser = userDao.getByUsername(username);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newUser;
    }

    public static UserEntity login(String username, String password) {
        UserDaoImpl userDao = new UserDaoImpl();
        UserEntity existingUser = null;

        try {
            existingUser = userDao.getByUsername(username);

            if (Objects.equals(existingUser.getPassword(), password)) {
                System.out.println("Successful login!");
            } else {
                System.out.println("Wrong username or password.");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return existingUser;
    }

    public static void leaveReview(String review, int rating, UserEntity userEntity, BookEntity bookEntity) {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        ReviewEntity reviewEntity = new ReviewEntity(
                userEntity.getId(),
                bookEntity.getId(),
                review,
                rating
        );

        try {
            reviewDao.insert(reviewEntity);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void updateUserReview(String review, int rating, ReviewEntity reviewEntity) {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        reviewEntity.setReview(review);
        reviewEntity.setRating(rating);

        try {
            reviewDao.updateUserReview(reviewEntity);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void removeReview(ReviewEntity reviewEntity) {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        try {
            reviewDao.deleteById(reviewEntity.getId());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void printReviews() {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        try {
            var reviews = reviewDao.getAll();
            for (var review : reviews) {
                System.out.println(review);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void printReviews(UserEntity userEntity) {
        var userReviews = getUserReviews(userEntity);
         for (var review : userReviews) {
             System.out.println(review);
         }
    }

    public static List<ReviewEntity> getUserReviews(UserEntity userEntity) {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        List<ReviewEntity> userReviews = new LinkedList<>();

        try {
            userReviews = reviewDao.getByUserId(userEntity.getId());

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return userReviews;
    }

    public static List<BookEntity> searchBookByQuery(String query) {
        BookDaoImpl bookDao = new BookDaoImpl();
        query = "%" + query + "%";
        List<BookEntity> bookEntities = new LinkedList<>();

        try {
            bookEntities = bookDao.getByQuery(query);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return bookEntities;
    }

    public static List<List<String>> readCsvFile(String path) {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                records.add(Arrays.asList(line));
            }
        } catch (IOException | CsvValidationException e) {
            e.getMessage();
        }

        return records;
    }

    public static void fillBooksTable(List<List<String>> books) {
        List<BookEntity> booksFromFile = new LinkedList<>();
        for (var book : books) {
            booksFromFile.add(new BookEntity(
                    book.get(0),
                    book.get(1),
                    book.get(2),
                    Integer.parseInt(book.get(3))
            ));
        }

        BookDaoImpl bookDao = new BookDaoImpl();
        try {
            var booksFromTable = bookDao.getAll();
            booksFromFile.removeAll(booksFromTable);
            bookDao.insert(booksFromFile);
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
