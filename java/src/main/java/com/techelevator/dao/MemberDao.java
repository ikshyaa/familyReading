package com.techelevator.dao;

import com.techelevator.model.Member;
import com.techelevator.model.RegisterMember;

import java.util.List;

public interface MemberDao {

    public Member getMemberById(Long id);

    public Member addMember(Member member);

    public void registerNewMember(RegisterMember registerMember);
    public List<Member> getMembers(Long id);

    boolean ifParent(long userId);
}

