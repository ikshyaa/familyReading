package com.techelevator.dao;

import com.techelevator.model.Member;
import com.techelevator.model.RegisterMember;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMemberDao implements MemberDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserDao userDao;

    @Override
    public Member getMemberById(Long userId) {
        //incomplete sry
        String sql = "SELECT u.user_id, u.username, fm.is_parent, f.family_id " +
                "FROM family_member as fm " +
                "JOIN users as u ON fm.user_id = u.user_id ";

        return null;
    }

    @Override
    public Member addMember(Member member) {

        Long user_id ;


        String sql = "INSERT INTO family_member(user_id, family_id, is_Parent) " +
                "VALUES(?, ?, ? ) " +
                "RETURNING user_id ;";

        user_id = jdbcTemplate.queryForObject(sql
                , long.class
                , member.getUserId()
                ,member.getFamilyId()
                ,member.isParent());

        if(user_id != null ) return member;
        else return null;

    }

    @Override
    public void registerNewMember(RegisterMember registerMember) {
        System.out.println(2);
        System.out.println(registerMember);

        userDao.create(registerMember.getUsername()
                , registerMember.getPassword()
                , registerMember.getRole());

        Member member = new Member();

        member.setUsername(registerMember.getUsername());
        member.setUserId((long)userDao.findIdByUsername(registerMember.getUsername()));
        member.setParent(registerMember.getIsParent());
        member.setFamilyId(registerMember.getFamilyId());

        addMember(member);

    }

    @Override
    public List<Member> getMembers(Long id) {
        List<Member> members = new ArrayList<>();

        String sql ="SELECT u.user_id\n" +
                "\t,u.username\n" +
                "\t, fm.is_parent \n" +
                "\t, f.family_id\n" +
                "FROM family_member as fm\n" +
                "JOIN users as u\n" +
                "ON fm.user_id = u.user_id\n" +
                "join family as f\n" +
                "ON f.family_id = fm.family_id\n" +
                "WHERE fm.family_id = ?\n";


        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);

        while(rowSet.next()){
            Member member = new Member();
            member = rowToMapMember(rowSet);
            members.add(member);


        }



        return members;
    }

    @Override
    public boolean ifParent(long userId) {

        String sql = "SELECT is_parent from family_member where user_id = ? ";

        Boolean isParent = jdbcTemplate.queryForObject(sql, Boolean.class, userId);
        return isParent;
    }

    private Member rowToMapMember(SqlRowSet rowSet) {
        Member member = new Member();
        member.setUsername(rowSet.getString("username"));
        member.setParent(rowSet.getBoolean("is_parent"));
        member.setUserId(rowSet.getLong("user_id"));
        member.setFamilyId(rowSet.getLong("family_id"));
        return  member;
    }


}
