package com.techelevator.model;

import java.util.Date;

public class ReadingLog {

    private int readingLogId;
    private int minutes;
    private String format;
    private Date date;
    private int startingPage;
    private int endPage;
    private int bookId;
    private int userId;

    private String notes;

    public ReadingLog() {
    }

    public ReadingLog(int readingLogId, int minutes, String format, Date date, int startingPage, int endPage, int bookId, int userId, String notes) {
        this.readingLogId = readingLogId;
        this.minutes = minutes;
        this.format = format;
        this.date = date;
        this.startingPage = startingPage;
        this.endPage = endPage;
        this.bookId = bookId;
        this.userId = userId;
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

}
