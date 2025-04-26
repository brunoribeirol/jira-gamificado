package com.cesar.school.core.gamification.entity;


import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.gamification.vo.RewardType;
import com.cesar.school.core.shared.MemberId;

public class Reward {

    private final RewardId id;
    private String description;
    private int requiredPoints;
    private RewardType type; // Cupom, Folga ou Destaque
    private MemberId createdBy;

    public Reward(RewardId id, String description, int requiredPoints, RewardType type, MemberId createdBy) {
        if (requiredPoints <= 0) {
            throw new IllegalArgumentException("Required points must be greater than zero.");
        }
        this.id = id;
        this.description = description;
        this.requiredPoints = requiredPoints;
        this.type = type;
        this.createdBy = createdBy;
    }

    public boolean canBeUnlockedBy(int points) {
        return points >= requiredPoints;
    }

    public RewardId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public RewardType getType() {
        return type;
    }

    public MemberId getCreatedBy() {
        return createdBy;
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }

    public void updateRequiredPoints(int newRequiredPoints) {
        if (newRequiredPoints <= 0) {
            throw new IllegalArgumentException("Required points must be greater than zero.");
        }
        this.requiredPoints = newRequiredPoints;
    }

    public void updateType(RewardType newType) {
        this.type = newType;
    }
}
