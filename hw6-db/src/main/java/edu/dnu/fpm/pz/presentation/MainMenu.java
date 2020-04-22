package edu.dnu.fpm.pz.presentation;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.dnu.fpm.pz.dao.impl.BookDaoImpl;
import edu.dnu.fpm.pz.dao.impl.ReviewDaoImpl;
import edu.dnu.fpm.pz.dao.impl.UserDaoImpl;
import edu.dnu.fpm.pz.entity.BookEntity;
import edu.dnu.fpm.pz.entity.ReviewEntity;
import edu.dnu.fpm.pz.entity.UserEntity;
import edu.dnu.fpm.pz.presentation.console.Menu;
import edu.dnu.fpm.pz.presentation.console.MenuItem;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.sql.SQLException;
import java.util.*;

public class MainMenu {
    private UserEntity CURRENT_USER = null;

    UserDaoImpl userDao = new UserDaoImpl();
    ReviewDaoImpl reviewDao = new ReviewDaoImpl();
    BookDaoImpl bookDao = new BookDaoImpl();

    Scanner scanner = new Scanner(System.in);

    public void getMainMenu() {
        Menu menu = new Menu();
        menu.setTitle("Project 6 by Joseph Aveltsev.");
        menu.addItem(new MenuItem("Login", this, "login"));
        menu.addItem(new MenuItem("Registration", this, "registration"));
        menu.addItem(new MenuItem("Fill up books table", this, "fillBooksTable"));
        menu.execute();
    }

    public void login() throws SQLException {
        CURRENT_USER = null;
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        CURRENT_USER = userDao.getByUsername(username);

        if (Objects.nonNull(CURRENT_USER) &&
                CURRENT_USER.getPassword().equals(password)) {
            System.out.println("Successful login!");

            getLoggedUserMenu();

        } else {
            System.out.println("Wrong username or password.");
        }
    }

    public void registration() throws SQLException, NoSuchObjectException {
        CURRENT_USER = null;
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        var existingUser = userDao.getByUsername(username);

        if (Objects.nonNull(existingUser)) {
            System.out.println("Username is taken!");
        }
        userDao.insert(new UserEntity(username, password));
        CURRENT_USER = userDao.getByUsername(username);
        getLoggedUserMenu();
    }

    public void getLoggedUserMenu() {
        Menu menu = new Menu();
        menu.setTitle("You are logged in");
        menu.addItem(new MenuItem("View your reviews", this, "printUserReviews"));
        menu.addItem(new MenuItem("Search for a book", this, "bookSearch"));
        menu.execute();
    }

    public void printUserReviews() throws SQLException {
        var reviews = reviewDao.getByUserId(CURRENT_USER.getId());
        Menu menu = new Menu();
        menu.setTitle("Your reviews");

        for (var review : reviews) {
            Class[] params = new Class[]{int.class};
            Object[] args = new Object[]{review.getId()};

            menu.addItem(new MenuItem(review.toString(), this,
                    "editReview", params, args));
        }
        menu.execute();
    }

    @SneakyThrows
    public void editReview(int id) {
        System.out.println("Review: ");
        String review = scanner.nextLine();
        System.out.println("Rating: ");
        int rating = scanner.nextInt();

        ReviewEntity reviewEntity = new ReviewEntity(
                id,
                CURRENT_USER.getId(),
                0,
                review,
                rating
        );

        reviewDao.update(reviewEntity);
    }

    public void bookSearch() throws SQLException {
        System.out.println("Input book isbn, title or author:");
        String query = scanner.nextLine();
        query = "%" + query + "%";

        BookDaoImpl bookDao = new BookDaoImpl();
        List<BookEntity> books = bookDao.getByQuery(query);

        Menu menu = new Menu();
        menu.setTitle("Books");

        for (var book : books) {
            Class[] params = new Class[]{int.class};
            Object[] args = new Object[]{book.getId()};

            menu.addItem(new MenuItem(book.toString(), this,
                    "printBookReviews", params, args));
        }
        menu.execute();
    }

    public void printBookReviews(int bookId) throws SQLException {
        var reviews = reviewDao.getByBookId(bookId);

        Menu menu = new Menu();
        menu.setTitle("Book reviews");
        Class[] params = new Class[]{int.class};
        Object[] args = new Object[]{bookId};
        menu.addItem(new MenuItem("Leave review", this, "leaveReview", params, args));
        for (var review : reviews) {
            System.out.println(review);
        }
        menu.execute();
    }

    public void leaveReview(int bookId) throws SQLException, NoSuchObjectException {
        System.out.println("Review: ");
        String review = scanner.nextLine();
        System.out.println("Rating: ");
        int rating = scanner.nextInt();

        ReviewEntity reviewEntity = new ReviewEntity(
                CURRENT_USER.getId(),
                bookId,
                review,
                rating
        );

        reviewDao.insert(reviewEntity);
    }

    public void fillBooksTable() throws IOException, CsvValidationException, SQLException {
        System.out.println("Input csv file name: ");
        String path = "src/main/resources/" + scanner.nextLine();

        List<List<String>> books = new ArrayList<>();
        CSVReader csvReader = new CSVReader(new FileReader(path));
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            books.add(Arrays.asList(line));
        }

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
        var booksFromTable = bookDao.getAll();
        booksFromFile.removeAll(booksFromTable);
        bookDao.insert(booksFromFile);
    }
}
