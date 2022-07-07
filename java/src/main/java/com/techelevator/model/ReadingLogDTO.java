package com.techelevator.model;

import java.util.Date;

public class ReadingLogDTO {

    private int readingLogId;
    private int minutes;
    private String format;
    private Date date;
    private int startingPage;
    private int endPage;
    private int bookId;
    private int userId;
    private String title;
    private String username;
    private int pagesRead;

    private String notes;

    public ReadingLogDTO() {
    }

    public ReadingLogDTO(int readingLogId, int minutes, String format, Date date,
                      int startingPage, int endPage, int bookId, int userId,
                      String title, String username, int pagesRead, String notes) {
        this.readingLogId = readingLogId;
        this.minutes = minutes;
        this.format = format;
        this.date = date;
        this.startingPage = startingPage;
        this.endPage = endPage;
        this.bookId = bookId;
        this.userId = userId;
        this.title = title;
        this.username = username;
        this.pagesRead = pagesRead;
        this.notes = notes;
    }

    public int getReadingLogId() {
        return readingLogId;
    }

    public void setReadingLogId(int readingLogId) {
        this.readingLogId = readingLogId;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStartingPage() {
        return startingPage;
    }

    public void setStartingPage(int startingPage) {
        this.startingPage = startingPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }
}
