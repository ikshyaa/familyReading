package com.techelevator.dao;

import com.techelevator.model.Book;

import java.util.List;

public interface BookDao {

    public Book getBookById(Long bookId);

    public List<Book> getListOfBooksByUser(Long userId);

    public List<Book> getAllBooks();

    public Book addBook(Book book);

}
