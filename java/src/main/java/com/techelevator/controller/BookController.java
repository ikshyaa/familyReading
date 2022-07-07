package com.techelevator.controller;

import com.techelevator.dao.BookDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Book;
import com.techelevator.model.Family;
import com.techelevator.model.Member;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @ApiOperation("get all books by userId")
    @RequestMapping(path="/myBooks/{userId}", method = RequestMethod.GET)
    public List<Book> getAllBooksByUserId(@ApiParam("principal") Principal principal, @PathVariable int userId) {

        //long userId =(long)userDao.findIdByUsername(principal.getName());

        return bookDao.getListOfBooksByUser((long)userId);

    }

    @ApiOperation("get all books")
    @RequestMapping(path="/books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {

        return bookDao.getAllBooks();

    }


    @ApiOperation("get a book by bookId")
    @RequestMapping(path="/books/{id}", method = RequestMethod.GET)
    public Book getBookByBookId(@PathVariable @ApiParam("book id") Long id) {

        return bookDao.getBookById(id);

    }
//
//    @ApiOperation("get a book by bookId")
//    @RequestMapping(path="/books/{id}", method = RequestMethod.GET)
//    public Book getBookByBookChildId(@PathVariable @ApiParam("book id") user user) {
//
//        return bookDao.getBookById(id);
//
//    }

    @ApiOperation("adding a book")
    @RequestMapping(path="/add-book", method = RequestMethod.POST)
    public Book addbook(@RequestBody @ApiParam("book object") Book book) {

        return bookDao.addBook(book);

    }

    @ApiOperation("get all books by userId")
    @RequestMapping(path="/userBooks", method = RequestMethod.GET)
    public List<Book> getBooksByUserId(@ApiParam("userId ") @RequestBody long id) {

//        long userId =(long)userDao.findIdByUsername(principal.getName());
        System.out.println(bookDao.getListOfBooksByUser(id));

        return bookDao.getListOfBooksByUser(id);

    }

}
