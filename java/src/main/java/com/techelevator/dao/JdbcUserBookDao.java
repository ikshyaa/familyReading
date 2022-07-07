package com.techelevator.dao;

import com.techelevator.model.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserBookDao implements UserBookDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserDao userDao;

    @Override
    public void assignBookToUser(UserBook userBook) {

        Long userId = (long)userDao.findIdByUsername(userBook.getUsername());
        String sql = "INSERT INTO user_book(user_id,book_id) VALUES (?,?);";

        jdbcTemplate.update(sql, userId, userBook.getBookId());

    }

}
