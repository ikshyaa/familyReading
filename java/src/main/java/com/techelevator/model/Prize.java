package com.techelevator.model;

import java.util.Date;

public class Prize {

    private long prizeId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int milestone; //minutes
    private  boolean isActive;
    private int numberOfWinners;

    public Prize() {
    }

    public Prize(long prizeId, String name, String description, Date startDate, Date endDate, int milestone, boolean isActive, int numberOfWinners) {
        this.prizeId = prizeId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.milestone = milestone;
        this.isActive = isActive;
        this.numberOfWinners = numberOfWinners;
    }

    public long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(long prizeId) {
        this.prizeId = prizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMilestone() {
        return milestone;
    }

    public void setMilestone(int milestone) {
        this.milestone = milestone;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public int getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setNumberOfWinners(int numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }
}
