package com.techelevator.model;

public class Member {
    //New RegisterMember object (make it this one)
    // adding new fam member - this class necessary? passing too much data? do we only need to pass isParent
    private Long userId;
    private String username;
    private boolean isParent;
    private Long familyId;
//    private String familyName;

    public Member () {}

    public Member(Long userId, String username, boolean isParent, Long familyId) {
        this.userId = userId;
        this.username = username;
        this.isParent = isParent;
        this.familyId = familyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
}
