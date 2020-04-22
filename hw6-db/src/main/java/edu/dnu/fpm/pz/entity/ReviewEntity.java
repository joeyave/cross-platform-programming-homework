package edu.dnu.fpm.pz.entity;

public class ReviewEntity {
    private int id;
    private int userId;
    private int bookId;
    private String review;
    private int rating;

    public ReviewEntity(int userId, int bookId, String review, int rating) {
        this.userId = userId;
        this.bookId = bookId;
        this.review = review;
        this.rating = rating;
    }

    public ReviewEntity(int id, int userId, int bookId, String review, int rating) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.review = review;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int book_id) {
        this.bookId = book_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ReviewEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", book_id=" + bookId +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
