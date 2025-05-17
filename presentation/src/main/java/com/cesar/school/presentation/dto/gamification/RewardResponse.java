package com.cesar.school.presentation.dto.gamification;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardType;

public class RewardResponse {

    public int id;
    public String description;
    public int requiredPoints;
    public RewardType type;
    public int createdBy;

    public static RewardResponse fromDomain(Reward reward) {
        RewardResponse response = new RewardResponse();
        response.id = reward.getId().getValue();
        response.description = reward.getDescription();
        response.requiredPoints = reward.getRequiredPoints();
        response.type = reward.getType();
        response.createdBy = reward.getCreatedBy().getValue();
        return response;
    }
}
