package com.techelevator.model;

public class UserBook {

    private String username;
    private Long userId;
    private Long bookId;

    public UserBook() {
    }

    public UserBook(String username, Long userId, Long bookId) {
        this.username = username;
        this.userId = userId;
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
