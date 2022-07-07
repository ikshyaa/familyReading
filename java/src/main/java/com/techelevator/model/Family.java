package com.techelevator.model;

import java.util.List;

public class Family {

    private Long familyId;
    private String familyName;
//    private boolean isParent;
//    private String username;
//    private Long userId;

//    private List<>

    public Family(Long familyId, String familyName) {
        this.familyId = familyId;
        this.familyName = familyName;
//        this.isParent = isParent;
//        this.username = username;
//        this.userId = userId;
    }

    public Family() {}

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
