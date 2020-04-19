package edu.dnu.fpm.pz.entities;

public class ReviewEntity {
    private int id;
    private int userId;
    private int book_id;
    private String review;
    private int rating;

    public ReviewEntity(int id, int userId, int book_id, String review, int rating) {
        this.id = id;
        this.userId = userId;
        this.book_id = book_id;
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

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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
                ", book_id=" + book_id +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
