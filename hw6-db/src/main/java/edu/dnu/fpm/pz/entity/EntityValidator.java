package edu.dnu.fpm.pz.entity;

import java.rmi.NoSuchObjectException;
import java.util.Objects;

public class EntityValidator {
    public void bookEntityValidate(BookEntity bookEntity) throws NoSuchObjectException {
        if (Objects.nonNull(bookEntity)) {
            if (bookEntity.getId() < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (Objects.isNull(bookEntity.getAuthor())) {
                throw new NoSuchObjectException("Author is null.");
            }
            if (Objects.isNull(bookEntity.getTitle())) {
                throw new NoSuchObjectException("Title is null.");
            }
            if (bookEntity.getYear() < 0) {
                throw new IndexOutOfBoundsException("Negative year.");
            }
            if (Objects.isNull(bookEntity.getIsbn()) || bookEntity.getIsbn().length() != 10) {
                throw new NoSuchObjectException("Wrong isbn.");
            }
        } else {
            throw new NoSuchObjectException("Book entity is null.");
        }
    }

    public void userEntityValidate(UserEntity userEntity) throws NoSuchObjectException {
        if (Objects.nonNull(userEntity)) {
            if (userEntity.getId() < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (Objects.isNull(userEntity.getName())) {
                throw new NoSuchObjectException("Username is null");
            }
            if (Objects.isNull(userEntity.getPassword())) {
                throw new NoSuchObjectException("Password is null");
            }
        } else {
            throw new NoSuchObjectException("User is null.");
        }
    }

    public void reviewEntityValidate(ReviewEntity reviewEntity) throws NoSuchObjectException {
        if (Objects.nonNull(reviewEntity)) {
            if (reviewEntity.getId() < 0 || reviewEntity.getBookId() < 0 || reviewEntity.getUserId() < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (Objects.isNull(reviewEntity.getReview())) {
                throw new NoSuchObjectException("Review is empty.");
            }
            if (reviewEntity.getRating() < 0 || reviewEntity.getRating() > 5) {
                throw new IndexOutOfBoundsException("Rating is null.");
            }
        } else {
            throw new NoSuchObjectException("Review is null.");
        }
    }

}
