package com.techelevator.dao;

import com.techelevator.model.Family;
import com.techelevator.model.Member;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component
public class JdbcFamilyDao implements FamilyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MemberDao memberDao;

    @Override
    public String getFamilyNameByFamilyId(Long familyId) {
        String familyName = null;

        String sql = "SELECT family_name FROM family WHERE family_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);
        if (results.next()) {
            familyName = results.getString("family_name");
        }
        return familyName;
    }

    @Override
    public Long getFamilyIdByFamilyName(String name) {
        Long familyId = null;

        String sql = "SELECT family_id FROM family WHERE family_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        if (results.next()) {
            familyId = results.getLong("family_id");
        }

        return familyId;
    }

    public Family getFamilyById(Long familyId) {
        Family family = null;

        String sql = "SELECT family_id, family_name FROM family WHERE family_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, familyId);

        if (results.next()) {
            family = mapRowToFamily(results);
        }
        return family;
    }

    @Override
    public Family getFamilyByUserId(Long userId) {
        Family family = new Family();

        String sql = "SELECT family.family_id, family.family_name FROM family " +
                "JOIN family_member ON family.family_id = family_member.family_id " +
                "WHERE family_member.user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);

        if (results.next()) {
            family = mapRowToFamily(results);
        }

        return family;
    }

    @Override
    @Transactional
    public Family makeNewFamily(Family newFam, String name) {
        Long familyId;
        Family family = new Family();
        Member member = new Member();

        String sql = "INSERT INTO family (family_name) VALUES (?) RETURNING family_id;";
        familyId = jdbcTemplate.queryForObject(sql, Long.class, newFam.getFamilyName());
        //jdbcTemplate.update(sql, newFam.getFamilyName());

        member.setUserId( (long)userDao.findIdByUsername(name) );
        member.setFamilyId(familyId);
        member.setUsername(name);
        member.setParent(true);

        memberDao.addMember(member);

//        We need to make sure both tables are updated and make Transactional comment work!

        return getFamilyById(familyId);
    }

    private Family mapRowToFamily(SqlRowSet results) {
        Family family = new Family();

        family.setFamilyId(results.getLong("family_id"));
        family.setFamilyName(results.getString("family_name"));

        return family;
    }

    @Override
    public long getFamilyIDByUserId(Long userId) {
        Family family = new Family();

        String sql = "SELECT family.family_id, family.family_name FROM family " +
                "JOIN family_member ON family.family_id = family_member.family_id " +
                "WHERE family_member.user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);

        if (results.next()) {
            family = mapRowToFamily(results);
        }

        return family.getFamilyId();
    }
}
