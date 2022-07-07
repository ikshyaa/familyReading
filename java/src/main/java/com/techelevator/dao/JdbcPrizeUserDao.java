package com.techelevator.dao;

import com.techelevator.model.PrizeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcPrizeUserDao implements PrizeUserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;



    @Override
    public void addPrizeUser(PrizeUser prizeUser) {

        String sql = "Insert Into prize_user(prize_id, user_id, reachedGoal)" +
                " Values (?,?,false) ;";

        jdbcTemplate.update(sql, prizeUser.getPrize_id(), prizeUser.getUser_id());
    }
}
