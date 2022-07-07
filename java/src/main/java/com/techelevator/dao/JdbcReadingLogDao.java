package com.techelevator.dao;

import com.techelevator.model.ReadingLog;
import com.techelevator.model.ReadingLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReadingLogDao implements ReadingLogDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public ReadingLog getReadingLogById(int logId) {
        ReadingLog readingLog = new ReadingLog();

        String sql = "SELECT readingLog_id, book_id, user_id, minutes, " +
                "format, date, starting_page, end_page, notes FROM reading_log " +
                "WHERE readingLog_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, logId);

        if (results.next()) {
            readingLog = mapRowToReadingLog(results);
        }

        return readingLog;
    }

    @Override
    public List<ReadingLog> getReadingLogByUserId(int userId) {
        List<ReadingLog> list = new ArrayList<>();
        ReadingLog readingLog = new ReadingLog();

        String sql= "SELECT readingLog_id, book_id, user_id, minutes, " +
                "format, date, starting_page, end_page, notes " +
                "FROM reading_log as rl " +
                "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while (results.next()) {
            readingLog = mapRowToReadingLog(results);
            list.add(readingLog);
        }

        return list;
    }

    @Override
    public List<ReadingLogDTO> getReadingLogDTOByUserId(int userId) {
        List<ReadingLogDTO> list = new ArrayList<>();
        ReadingLogDTO readingLog = new ReadingLogDTO();

        String sql = "SELECT rl.readingLog_id, b.title, u.username, rl.minutes, rl.format, rl.date, " +
                "rl.starting_page, rl.end_page, (rl.end_page - rl.starting_page) as pagesRead, rl.notes, " +
                "rl.book_id, rl.user_id " +
                "FROM reading_log as rl " +
                "JOIN book as b ON rl.book_id = b.book_id " +
                "JOIN users as u ON rl.user_id = u.user_id " +
                "WHERE u.user_id = ?;";

//        String sql= "SELECT readingLog_id, book_id, user_id, minutes, " +
//                "format, date, starting_page, end_page, notes " +
//                "FROM reading_log as rl " +
//                "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while (results.next()) {
            readingLog = mapRowToReadingLogDTO(results);
            list.add(readingLog);
        }

        return list;
    }

    @Override
    public int getTotalReadingTime(long userId) {

        String sql = "select sum(minutes) as minutesRead " +
                "from reading_log " +
                "where user_id = ?";

        Integer totalMinutes = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return totalMinutes;
    }

    @Override
    public List<ReadingLog> getReadingLogByFamilyId(int familyId) {
        ReadingLog readingLog = new ReadingLog();
        List<ReadingLog> list = new ArrayList<>();

        String sql= "SELECT readingLog_id, rl.book_id, rl.user_id, minutes, " +
                "format, date, starting_page, end_page, notes " +
                "FROM reading_log as rl " +
                "JOIN user_book ON rl.user_id = user_book.user_id " +
                "JOIN family_member ON family_member.user_id = user_book.user_id " +
                "WHERE family_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);

        while(results.next()) {
            readingLog = mapRowToReadingLog(results);
            list.add(readingLog);
        }

        return list;
    }

    @Override
    public ReadingLog addNewReadingLog(ReadingLog log) {
        Long logId;
        ReadingLog logCheck = new ReadingLog();

        String sql = "INSERT INTO reading_log (book_id, user_id, minutes, " +
                "format, date, starting_page, end_page, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING readingLog_id;";

        logId = jdbcTemplate.queryForObject(sql, Long.class, log.getBookId(), log.getUserId(), log.getMinutes(),
            log.getFormat(), log.getDate(), log.getStartingPage(), log.getEndPage(), log.getNotes());

        if (logId != null) {
            logCheck = getReadingLogById(logId.intValue());
        }
        
        return logCheck;
    }

    private ReadingLogDTO mapRowToReadingLogDTO(SqlRowSet sqlRowSet) {
        ReadingLogDTO readingLogDTO = new ReadingLogDTO();

        readingLogDTO.setReadingLogId(sqlRowSet.getInt("readingLog_id"));
        readingLogDTO.setMinutes(sqlRowSet.getInt("minutes"));
        readingLogDTO.setFormat(sqlRowSet.getString("format"));
        readingLogDTO.setDate(sqlRowSet.getDate("date"));
        readingLogDTO.setStartingPage(sqlRowSet.getInt("starting_page"));
        readingLogDTO.setEndPage(sqlRowSet.getInt("end_page"));
        readingLogDTO.setBookId(sqlRowSet.getInt("book_id"));
        readingLogDTO.setUserId(sqlRowSet.getInt("user_id"));
        readingLogDTO.setNotes(sqlRowSet.getString("notes"));
        readingLogDTO.setTitle((sqlRowSet.getString("title")));
        readingLogDTO.setUsername(sqlRowSet.getString("username"));
        readingLogDTO.setPagesRead(sqlRowSet.getInt("pagesRead"));

        return readingLogDTO;
    }

    private ReadingLog mapRowToReadingLog(SqlRowSet sqlRowSet) {
        ReadingLog readingLog = new ReadingLog();

        readingLog.setReadingLogId(sqlRowSet.getInt("readingLog_id"));
        readingLog.setMinutes(sqlRowSet.getInt("minutes"));
        readingLog.setFormat(sqlRowSet.getString("format"));
        readingLog.setDate(sqlRowSet.getDate("date"));
        readingLog.setStartingPage(sqlRowSet.getInt("starting_page"));
        readingLog.setEndPage(sqlRowSet.getInt("end_page"));
        readingLog.setBookId(sqlRowSet.getInt("book_id"));
        readingLog.setUserId(sqlRowSet.getInt("user_id"));
        readingLog.setNotes(sqlRowSet.getString("notes"));

        return readingLog;
    }
}
