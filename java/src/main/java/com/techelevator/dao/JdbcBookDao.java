package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBookDao implements BookDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserDao userDao;

    @Override
    public Book getBookById(Long bookId) {

        Book book  = new Book();

        String sql = "SELECT book_id, title, author, isbn From book Where book_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, bookId);

        if(rowSet.next()) {
            book = rowToMapBook(rowSet);
        }

        return book;
    }

    @Override
    public List<Book> getListOfBooksByUser(Long userId) {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        String sql = "SELECT book.book_id,book.title,book.author,book.isbn  From book " +
                "Join user_book On book.book_id = user_book.book_id " +
                "Where user_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while(rowSet.next()){
            book = rowToMapBook(rowSet);
            books.add(book);
        }

        return books;
    }

    @Override
    public List<Book> getAllBooks() {


        List<Book> books = new ArrayList<>();
        Book book = new Book();

        String sql = "SELECT book_id,title,author,isbn  From book " ;

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while(rowSet.next()){
            book = rowToMapBook(rowSet);
            books.add(book);
        }

        return books;
    }

    @Override
    public Book addBook(Book book) {

        Book bookCheck;
        Long bookId;

        String sql = "INSERT INTO book (title, author, isbn) " +
                "VALUES (?,?,?) RETURNING book_id;";

        bookId = jdbcTemplate.queryForObject(sql, Long.class, book.getTitle(),
                book.getAuthor(), book.getIsbn());

        bookCheck = getBookById(bookId);

        if (bookId != null) return bookCheck;
       else return null;
    }

    private Book rowToMapBook(SqlRowSet sqlRowSet) {
        Book book = new Book();

        book.setBookId(sqlRowSet.getLong("book_id"));
        book.setTitle(sqlRowSet.getString("title"));
        book.setAuthor(sqlRowSet.getString("author"));
        book.setIsbn(sqlRowSet.getString("isbn"));

        return book;
    }
}
