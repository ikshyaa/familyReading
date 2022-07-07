package com.techelevator.controller;

import com.techelevator.dao.UserBookDao;
import com.techelevator.model.UserBook;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserBookController {

    @Autowired
    private UserBookDao userBookDao;

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Adding a book to a user")
    @RequestMapping(value = "/userBook", method = RequestMethod.POST)
    public void assignBookToUser(@RequestBody UserBook userBook) {
        userBookDao.assignBookToUser(userBook);
    }

}
