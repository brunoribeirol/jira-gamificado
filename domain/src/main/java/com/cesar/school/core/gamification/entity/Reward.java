package com.cesar.school.core.gamification.entity;

import com.cesar.school.core.gamification.vo.MemberId;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.gamification.vo.RewardType;

public class Reward {
    private RewardId id;
    private String description;
    private int requiredPoints;
    private RewardType type; // Enum: CUPOM, FOLGA, DESTAQUE
    private MemberId createdBy;

    public boolean canBeUnlockedBy(int points) {
        return points >= requiredPoints;
    }
}

