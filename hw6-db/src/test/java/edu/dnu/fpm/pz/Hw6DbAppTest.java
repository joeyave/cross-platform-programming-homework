package edu.dnu.fpm.pz;

import edu.dnu.fpm.pz.dao.impl.BookDaoImpl;
import edu.dnu.fpm.pz.dao.impl.ReviewDaoImpl;
import edu.dnu.fpm.pz.dao.impl.UserDaoImpl;
import edu.dnu.fpm.pz.entity.BookEntity;
import edu.dnu.fpm.pz.entity.ReviewEntity;
import edu.dnu.fpm.pz.entity.UserEntity;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class Hw6DbAppTest {
    BookDaoImpl bookDao = new BookDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();
    ReviewDaoImpl reviewDao = new ReviewDaoImpl();

    @SneakyThrows
    @After
    public void dropTables() {
        bookDao.deleteAll();
        userDao.deleteAll();
        reviewDao.deleteAll();
    }

    @SneakyThrows
    @Test
    public void BookDaoImplInsertTest() {
        // Given
        BookEntity expectedBookEntity = new BookEntity("123456789X", "title", "author", 2000);

        // When
        bookDao.insert(expectedBookEntity);
        var actualBookEntity = bookDao.getAll().getLast();

        // Then
        Assert.assertEquals(expectedBookEntity, actualBookEntity);
    }

    @SneakyThrows
    @Test
    public void BookDaoImplInsertListTest() {
        // Given
        BookEntity book1 = new BookEntity("1111111111", "title1", "author1", 2000);
        BookEntity book2 = new BookEntity("2222222222", "title2", "author2", 2000);
        BookEntity book3 = new BookEntity("3333333333", "title3", "author3", 2000);

        LinkedList<BookEntity> expectedBookEntity = new LinkedList<>();
        expectedBookEntity.add(book1);
        expectedBookEntity.add(book2);
        expectedBookEntity.add(book3);

        // When
        bookDao.insert(expectedBookEntity);
        var actualBookEntity = bookDao.getAll();

        // Then
        Assert.assertNotNull(actualBookEntity);
    }

    @SneakyThrows
    @Test
    public void BookDaoImplGetAllTest() {
        // Given
        BookEntity book1 = new BookEntity("1111111111", "title1", "author1", 2000);
        BookEntity book2 = new BookEntity("2222222222", "title2", "author2", 2000);
        BookEntity book3 = new BookEntity("3333333333", "title3", "author3", 2000);

        LinkedList<BookEntity> expectedBookEntity = new LinkedList<>();
        expectedBookEntity.add(book1);
        expectedBookEntity.add(book2);
        expectedBookEntity.add(book3);

        // When
        bookDao.insert(expectedBookEntity);
        var actualBookEntity = bookDao.getAll();

        // Then
        Assert.assertEquals(expectedBookEntity, actualBookEntity);
    }

    @SneakyThrows
    @Test
    public void BookDaoImplGetByIdTest() {
        // Given
        BookEntity expectedBookEntity = new BookEntity("1111111111", "title1", "author1", 2000);

        // When
        bookDao.insert(expectedBookEntity);
        expectedBookEntity = bookDao.getAll().getLast();
        var actualBookEntity = bookDao.getById(expectedBookEntity.getId());

        // Then
        Assert.assertEquals(expectedBookEntity.toString(), actualBookEntity.toString());
    }

    @SneakyThrows
    @Test
    public void BookDaoImplGetByQueryTest() {
        // Given
        BookEntity expectedBookEntity = new BookEntity("1111111111", "title1", "author1", 2000);

        // When
        bookDao.insert(expectedBookEntity);
        var actualBookEntity = bookDao.getByQuery("title1").getFirst();

        // Then
        Assert.assertEquals(expectedBookEntity, actualBookEntity);
    }

    @SneakyThrows
    @Test
    public void BookDaoImplUpdateTest() {
        // Given
        BookEntity expectedBookEntity = new BookEntity("1111111111", "title1", "author1", 2000);

        // When
        bookDao.insert(expectedBookEntity);
        expectedBookEntity = bookDao.getAll().getLast();
        expectedBookEntity.setAuthor("update");
        bookDao.update(expectedBookEntity);
        var actualBookEntity = bookDao.getAll().getLast();

        // Then
        Assert.assertEquals(expectedBookEntity.toString(), actualBookEntity.toString());
    }

    @SneakyThrows
    @Test
    public void BookDaoImplUpdateListTest() {
        // Given
        LinkedList<BookEntity> books = new LinkedList<>();
        BookEntity book1 = new BookEntity("1111111111", "title1", "author1", 2000);
        BookEntity book2 = new BookEntity("2222222222", "title2", "author2", 2000);
        BookEntity book3 = new BookEntity("3333333333", "title3", "author3", 2000);
        books.add(book1);
        books.add(book2);
        books.add(book3);

        // When
        bookDao.insert(books);
        LinkedList<BookEntity> expectedBookEntity = bookDao.getAll();
        expectedBookEntity.get(0).setTitle("update");
        expectedBookEntity.get(1).setTitle("update");
        expectedBookEntity.get(2).setTitle("update");


        bookDao.update(expectedBookEntity);
        var actualBookEntity = bookDao.getAll();

        // Then
        Assert.assertEquals(expectedBookEntity.toString(), actualBookEntity.toString());
    }

    @SneakyThrows
    @Test
    public void BookDaoImplDeleteByIdTest() {
        // Given
        BookEntity expectedBookEntity = new BookEntity("1111111111", "title1", "author1", 2000);

        // When
        bookDao.insert(expectedBookEntity);
        expectedBookEntity = bookDao.getAll().getLast();
        bookDao.deleteById(expectedBookEntity.getId());
        var actualBookEntity = bookDao.getAll();

        // Then
        Assert.assertEquals(actualBookEntity.size(), 0);
    }

    @SneakyThrows
    @Test
    public void BookDaoImplDeleteAllTest() {
        // Given
        BookEntity expectedBookEntity = new BookEntity("1111111111", "title1", "author1", 2000);

        // When
        bookDao.insert(expectedBookEntity);
        bookDao.deleteAll();
        var actualBookEntity = bookDao.getAll();

        // Then
        Assert.assertEquals(actualBookEntity.size(), 0);
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplInsertTest() {
        // Given
        ReviewEntity expectedReviewEntity = new ReviewEntity(1, 1, "review", 5);

        // When
        reviewDao.insert(expectedReviewEntity);
        expectedReviewEntity = reviewDao.getAll().getLast();
        var actualReviewEntity = reviewDao.getAll().getLast();

        // Then
        Assert.assertEquals(expectedReviewEntity.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplInsertListTest() {
        // Given
        ReviewEntity rev1 = new ReviewEntity(1, 1, "review", 5);
        ReviewEntity rev2 = new ReviewEntity(1, 1, "review", 5);
        ReviewEntity rev3 = new ReviewEntity(1, 1, "review", 5);


        LinkedList<ReviewEntity> expectedReviewEntity = new LinkedList<>();
        expectedReviewEntity.add(rev1);
        expectedReviewEntity.add(rev2);
        expectedReviewEntity.add(rev3);

        // When
        reviewDao.insert(expectedReviewEntity);
        expectedReviewEntity = reviewDao.getAll();
        var actualReviewEntity = reviewDao.getAll();

        // Then
        Assert.assertEquals(expectedReviewEntity.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplGetAllTest() {
        // Given
        ReviewEntity rev1 = new ReviewEntity(1, 1, "review", 5);
        ReviewEntity rev2 = new ReviewEntity(1, 1, "review", 5);
        ReviewEntity rev3 = new ReviewEntity(1, 1, "review", 5);


        LinkedList<ReviewEntity> expectedReviewEntity = new LinkedList<>();
        expectedReviewEntity.add(rev1);
        expectedReviewEntity.add(rev2);
        expectedReviewEntity.add(rev3);

        // When
        reviewDao.insert(expectedReviewEntity);
        expectedReviewEntity = reviewDao.getAll();
        var actualReviewEntity = reviewDao.getAll();

        // Then
        Assert.assertEquals(expectedReviewEntity.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplGetByIdTest() {
        // Given
        ReviewEntity expectedReviewEntity = new ReviewEntity(1, 1, "review", 5);

        // When
        reviewDao.insert(expectedReviewEntity);
        expectedReviewEntity = reviewDao.getAll().getLast();
        var actualReviewEntity = reviewDao.getById(expectedReviewEntity.getId());

        // Then
        Assert.assertEquals(expectedReviewEntity.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplGetByUserIdTest() {
        // Given
        ReviewEntity expectedReviewEntity = new ReviewEntity(1, 1, "review", 5);

        // When
        reviewDao.insert(expectedReviewEntity);
        expectedReviewEntity = reviewDao.getAll().getLast();
        var actualReviewEntity = reviewDao.getByUserId(1).getFirst();

        // Then
        Assert.assertEquals(expectedReviewEntity.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplGetByBookIdTest() {
        // Given
        ReviewEntity expectedReviewEntity = new ReviewEntity(1, 1, "review", 5);

        // When
        reviewDao.insert(expectedReviewEntity);
        expectedReviewEntity = reviewDao.getAll().getLast();
        var actualReviewEntity = reviewDao.getByBookId(1).getFirst();

        // Then
        Assert.assertEquals(expectedReviewEntity.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplUpdateTest() {
        // Given
        ReviewEntity expectedReviewEntity = new ReviewEntity(1, 1, "review", 5);

        // When
        reviewDao.insert(expectedReviewEntity);
        expectedReviewEntity = reviewDao.getAll().getLast();
        expectedReviewEntity.setReview("update");
        reviewDao.update(expectedReviewEntity);
        var actualBookEntity = reviewDao.getAll().getLast();

        // Then
        Assert.assertEquals(expectedReviewEntity.toString(), actualBookEntity.toString());
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplUpdateListTest() {
        // Given
        LinkedList<ReviewEntity> reviews = new LinkedList<>();
        ReviewEntity rev1 = new ReviewEntity(1, 1, "rev1", 5);
        ReviewEntity rev2 = new ReviewEntity(1, 1, "rev2", 5);
        ReviewEntity rev3 = new ReviewEntity(1, 1, "rev3", 5);
        reviews.add(rev1);
        reviews.add(rev2);
        reviews.add(rev3);

        // When
        reviewDao.insert(reviews);
        LinkedList<ReviewEntity> expectedReviewEntity = reviewDao.getAll();
        expectedReviewEntity.get(0).setReview("update");
        expectedReviewEntity.get(1).setReview("update");
        expectedReviewEntity.get(2).setReview("update");

        reviewDao.update(expectedReviewEntity);
        var actualReviewEntity = reviewDao.getAll();

        // Then
        Assert.assertEquals(expectedReviewEntity.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplDeleteByIdTest() {
        // Given
        ReviewEntity expectedReviewEntity = new ReviewEntity(1, 1, "rev1", 5);

        // When
        reviewDao.insert(expectedReviewEntity);
        expectedReviewEntity = reviewDao.getAll().getLast();
        reviewDao.deleteById(expectedReviewEntity.getId());
        var actualBookEntity = reviewDao.getAll();

        // Then
        Assert.assertEquals(actualBookEntity.size(), 0);
    }

    @SneakyThrows
    @Test
    public void ReviewDaoImplDeleteAllTest() {
        // Given
        ReviewEntity expectedReviewEntity = new ReviewEntity(1, 1, "rev1", 5);

        // When
        reviewDao.insert(expectedReviewEntity);
        reviewDao.deleteAll();
        var actualBookEntity = reviewDao.getAll();

        // Then
        Assert.assertEquals(actualBookEntity.size(), 0);
    }


    @SneakyThrows
    @Test
    public void UserDaoImplInsertTest() {
        // Given
        UserEntity expectedUserEntity = new UserEntity("login", "password");

        // When
        userDao.insert(expectedUserEntity);
        expectedUserEntity = userDao.getAll().getLast();

        // Then
        Assert.assertNotNull(expectedUserEntity.toString());

    }

    @SneakyThrows
    @Test
    public void UserDaoImplInsertListTest() {
        // Given
        UserEntity rev1 = new UserEntity("login", "password");
        UserEntity rev2 = new UserEntity("login", "password");
        UserEntity rev3 = new UserEntity("login", "password");

        LinkedList<UserEntity> expectedUserEntity = new LinkedList<>();
        expectedUserEntity.add(rev1);
        expectedUserEntity.add(rev2);
        expectedUserEntity.add(rev3);

        // When
        userDao.insert(expectedUserEntity);
        var actualUserEntity = userDao.getAll();

        // Then
        Assert.assertNotNull(actualUserEntity);
    }

    @SneakyThrows
    @Test
    public void UserDaoImplGetAllTest() {
        // Given
        UserEntity rev1 = new UserEntity("login", "password");
        UserEntity rev2 = new UserEntity("login", "password");
        UserEntity rev3 = new UserEntity("login", "password");


        LinkedList<UserEntity> expectedUserEntity = new LinkedList<>();
        expectedUserEntity.add(rev1);
        expectedUserEntity.add(rev2);
        expectedUserEntity.add(rev3);

        // When
        userDao.insert(expectedUserEntity);
        var actualUserEntity = userDao.getAll();

        // Then
        Assert.assertNotNull(actualUserEntity);
    }

    @SneakyThrows
    @Test
    public void UserDaoImplGetByIdTest() {
        // Given
        UserEntity expectedUserEntity = new UserEntity("login", "password");

        // When
        userDao.insert(expectedUserEntity);
        expectedUserEntity = userDao.getAll().getLast();
        var actualReviewEntity = userDao.getById(expectedUserEntity.getId());

        // Then
        Assert.assertEquals(expectedUserEntity.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void UserDaoImplGetByUsernameTest() {
        // Given
        UserEntity expectedUserEntity = new UserEntity("login", "password");

        // When
        userDao.insert(expectedUserEntity);
        expectedUserEntity = userDao.getAll().getLast();
        var actualReviewEntity = userDao.getByUsername(expectedUserEntity.getName());

        // Then
        Assert.assertEquals(expectedUserEntity.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void UserDaoImplUpdateTest() {
        // Given
        UserEntity expectedUserEntity = new UserEntity("login", "password");

        // When
        userDao.insert(expectedUserEntity);
        expectedUserEntity = userDao.getAll().getLast();
        expectedUserEntity.setPassword("update");
        userDao.update(expectedUserEntity);
        var actualBookEntity = userDao.getAll().getLast();

        // Then
        Assert.assertEquals(expectedUserEntity.toString(), actualBookEntity.toString());
    }

    @SneakyThrows
    @Test
    public void UserDaoImplUpdateListTest() {
        // Given
        UserEntity user1 = new UserEntity("user1", "user1");
        UserEntity user2 = new UserEntity("user2", "user2");
        UserEntity user3 = new UserEntity("user3", "user3");

        LinkedList<UserEntity> expectedUserEntity = new LinkedList<>();
        expectedUserEntity.add(user1);
        expectedUserEntity.add(user2);
        expectedUserEntity.add(user3);

        // When
        userDao.insert(expectedUserEntity);
        LinkedList<UserEntity> users = userDao.getAll();
        users.get(0).setPassword("update");
        users.get(1).setPassword("update");
        users.get(2).setPassword("update");

        userDao.update(users);
        var actualReviewEntity = userDao.getAll();

        // Then
        Assert.assertEquals(users.toString(), actualReviewEntity.toString());
    }

    @SneakyThrows
    @Test
    public void UserDaoImplDeleteByIdTest() {
        // Given
        UserEntity expectedUserEntity = new UserEntity("username", "username");

        // When
        userDao.insert(expectedUserEntity);
        expectedUserEntity = userDao.getAll().getLast();
        userDao.deleteById(expectedUserEntity.getId());
        var actualUserEntity = userDao.getAll();

        // Then
        Assert.assertEquals(actualUserEntity.size(), 0);
    }

    @SneakyThrows
    @Test
    public void UserDaoImplDeleteAllTest() {
        // Given
        UserEntity expectedUserEntity = new UserEntity("username", "username");

        // When
        userDao.insert(expectedUserEntity);
        userDao.deleteAll();
        var actualBookEntity = userDao.getAll();

        // Then
        Assert.assertEquals(actualBookEntity.size(), 0);
    }
}