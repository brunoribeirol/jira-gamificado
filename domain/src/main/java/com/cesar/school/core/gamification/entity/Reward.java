package com.cesar.school.core.gamification.entity;

import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.gamification.vo.RewardType;
import com.cesar.school.core.shared.MemberId;

import java.util.Objects;

public final class Reward {

    private final RewardId id;
    private final String description;
    private final int requiredPoints;
    private final RewardType type; // Cupom, Folga, Destaque
    private final MemberId createdBy;

    public Reward(RewardId id, String description, int requiredPoints, RewardType type, MemberId createdBy) {
        if (requiredPoints <= 0) {
            throw new IllegalArgumentException("Required points must be greater than zero.");
        }
        this.id = Objects.requireNonNull(id);
        this.description = Objects.requireNonNull(description);
        this.requiredPoints = requiredPoints;
        this.type = Objects.requireNonNull(type);
        this.createdBy = Objects.requireNonNull(createdBy);
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
}
