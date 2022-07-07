package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class RegisterMember {

    private Long userId; // get rid may be
    private String username;
    private boolean isParent;
    private Long familyId;
    private String familyName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty(message = "Please select a role for this user.")
    private String role;

    public RegisterMember() {
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

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegisterMember{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", isParent=" + isParent +
                ", familyId=" + familyId +
                ", familyName='" + familyName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
