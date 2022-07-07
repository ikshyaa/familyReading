package com.techelevator.model;

import java.util.Date;

public class PrizeUser {

    private int prize_id;
    private int user_id;
    private boolean reachedGoal;
    private Date reachedDate;

    public PrizeUser() {
    }

    public PrizeUser(int prize_id, int user_id, boolean reachedGoal) {
        this.prize_id = prize_id;
        this.user_id = user_id;
        this.reachedGoal = reachedGoal;

    }

    public int getPrize_id() {
        return prize_id;
    }

    public void setPrize_id(int prize_id) {
        this.prize_id = prize_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isReachedGoal() {
        return reachedGoal;
    }

    public void setReachedGoal(boolean reachedGoal) {
        this.reachedGoal = reachedGoal;
    }

    public Date getReachedDate() {
        return reachedDate;
    }

    public void setReachedDate(Date reachedDate) {
        this.reachedDate = reachedDate;
    }
}
