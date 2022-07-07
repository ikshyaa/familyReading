package com.techelevator.dao;

import com.techelevator.model.Family;

public interface FamilyDao {

    String getFamilyNameByFamilyId(Long familyId);

    Long getFamilyIdByFamilyName(String name);

    Family makeNewFamily(Family newFam, String userName);

    Family getFamilyById(Long familyId);

    Family getFamilyByUserId(Long userId);

    public long getFamilyIDByUserId(Long userId);

//    List<Members> getFamilyMembersByFamilyId(Long familyId);
    // sql statement
    //
}
