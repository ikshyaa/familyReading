package com.techelevator.dao;

import com.techelevator.model.Member;
import com.techelevator.model.Prize;
import com.techelevator.model.ReadingLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcPrizeDao implements PrizeDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Prize addPrize(Prize prize) {
        Long prizeId ;

        prize.setIsActive(true);

        String sql = "INSERT INTO prize(prize_name, description,start_date," +
                "end_date, milestone,isActive ,numberOfWinners) " +
                "VALUES(?, ?, ? , ?, ?, ?, ?) " +
                "RETURNING prize_id ;";

       prizeId = jdbcTemplate.queryForObject(sql
                , long.class
                ,prize.getName()
               ,prize.getDescription()
               ,prize.getStartDate()
               ,prize.getEndDate()
               ,prize.getMilestone()
               ,prize.getIsActive()
               ,prize.getNumberOfWinners());

       return getPrizeByPrizeId((prizeId));


    }



    @Override
    public List<Prize> getListOfPrizes(long familyId) {
        List<Prize> prizes = new ArrayList<>();

        String sql = "SELECT DISTINCT p.prize_id, p.prize_name, p.description, p.start_date," +
                " p.end_date, p.milestone,p.isActive ,p.numberOfWinners From Prize  as  p " +
                "JOIN prize_user as pu ON p.prize_id = pu.prize_id "+
                "JOIN family_member as fm ON pu.user_id = fm.user_id "+
                "Where  fm.family_id = ? ;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, familyId);

        while(rowSet.next()){
            Prize prize = new Prize();
            Date currentDate = new Date();
            prize = rowToMapPrize((rowSet));
            if ( prize.getEndDate().compareTo(currentDate) < 0) {
                prize.setIsActive(false);
                String inActiveSql = "UPDATE prize SET isActive = false WHERE prize_id = ?;";
                jdbcTemplate.update(inActiveSql, prize.getPrizeId());
            }
            prizes.add(prize);
        }


        return prizes;
    }

    @Override
    public Prize getPrizeByPrizeId(long prizeId) {
       Prize prize = new Prize();

        String sql = "SELECT prize_id, prize_name, description,start_date," +
                " end_date, milestone,isActive, numberOfWinners From Prize Where prize_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, prizeId);

        if(rowSet.next()) {
            prize = rowToMapPrize(rowSet);
        }


        return prize;
    }

    @Override
    public List<Member> getWinners(int prizeId, int familyId) {
        Prize prize = getPrizeByPrizeId(prizeId);
        List<Member> winners = new ArrayList<>();

        String sql = "SELECT reading_log.user_id, users.username, family_member.is_parent  from reading_log " +
                "                JOIN user_book ON user_book.user_id = reading_log.user_id " +
                "                JOIN family_member ON user_book.user_id = family_member.user_id " +
                "                JOIN users ON family_member.user_id = users.user_id " +
                "                WHERE (family_member.family_id = ?) AND (reading_log.date BETWEEN ? AND ?) " +
                "                GROUP BY reading_log.user_id, users.username, reading_log.date, family_member.is_parent " +
                "                HAVING SUM(minutes) >= ? " +
                "                order by  SUM(minutes) DESC,  reading_log.date ASC " +
                "                LIMIT ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId, prize.getStartDate(),
                prize.getEndDate(), prize.getMilestone(), prize.getNumberOfWinners());

        while(results.next()) {
            Member member = new Member();

            member.setFamilyId((long)familyId);
            member.setParent(results.getBoolean("is_parent"));
            member.setUserId(results.getLong("user_id"));
            member.setUsername(results.getString("username"));

            winners.add(member);
        }
        return winners;
    }

    @Override
    public List<ReadingLogDTO> getPrizeParticipants(int prizeId, int familyId) {
        Prize prize = getPrizeByPrizeId(prizeId);
        List<ReadingLogDTO> participants = new ArrayList<>();

        String sql ="SELECT reading_log.user_id, users.username, family_member.is_parent, SUM(reading_log.minutes) AS total_minutes from reading_log " +
                "                JOIN user_book ON user_book.user_id = reading_log.user_id " +
                "                JOIN family_member ON user_book.user_id = family_member.user_id " +
                "                JOIN users ON family_member.user_id = users.user_id " +
                "                WHERE (family_member.family_id = ?) AND (reading_log.date BETWEEN ? AND ?) " +
                "                GROUP BY reading_log.user_id, users.username, family_member.is_parent" +
                "                ORDER BY total_minutes DESC";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId, prize.getStartDate(),
                prize.getEndDate());

        while(results.next()) {
            ReadingLogDTO readingLogDTO = new ReadingLogDTO();

            readingLogDTO.setUsername(results.getString("username"));
            readingLogDTO.setMinutes((results.getInt("total_minutes")));

            participants.add(readingLogDTO);
        }
        return participants;
    }

    public void editPrize(Prize prize, long prizeId) {
        String sql = "UPDATE prize SET prize_name = ?, description= ?, start_date = ?," +
                " end_date = ?, milestone = ?, isActive = true, numberOfWinners = ? WHERE prize_id = ?;";
        jdbcTemplate.update(sql, prize.getName(), prize.getDescription(), prize.getStartDate(),
                prize.getEndDate(), prize.getMilestone(), prize.getNumberOfWinners(), prizeId);
    }

    public boolean deleteInactivePrize(long prizeId) {
        String sql = "BEGIN TRANSACTION; " +
                "DELETE from prize_user WHERE prize_id = ?; " +
                "DELETE from prize WHERE prize_id = ?; " +
                "COMMIT TRANSACTION;";
        return jdbcTemplate.update(sql, prizeId, prizeId) == 0;
    }

    private Prize rowToMapPrize(SqlRowSet sqlRowSet) {

        Prize prize = new Prize();

        prize.setPrizeId(sqlRowSet.getLong("prize_id"));
        prize.setName(sqlRowSet.getString("prize_name"));
        prize.setDescription(sqlRowSet.getString("description"));
        prize.setStartDate(sqlRowSet.getDate("start_date"));
        prize.setEndDate(sqlRowSet.getDate("end_date"));
        prize.setMilestone(sqlRowSet.getInt("milestone"));
        prize.setIsActive(sqlRowSet.getBoolean("isActive"));
        prize.setNumberOfWinners(sqlRowSet.getInt("numberOfWinners"));

        //number of prizes per month

        return prize;
    }

}
