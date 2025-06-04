package com.cesar.school.infrastructure.persistence.mapper.gamification;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.shared.vo.MemberId;
import com.cesar.school.infrastructure.persistence.entity.gamification.RewardEntity;

public class RewardMapper {

    public static RewardEntity toEntity(Reward reward) {
        Integer id = reward.getId() != null ? reward.getId().getValue() : null;
        return new RewardEntity(
                id,
                reward.getDescription(),
                reward.getRequiredPoints(),
                reward.getType(),
                reward.getCreatedBy().getValue()
        );
    }

    public static Reward toDomain(RewardEntity entity) {
        return new Reward(
                new RewardId(entity.getId()),
                entity.getDescription(),
                entity.getRequiredPoints(),
                entity.getType(),
                new MemberId(entity.getCreatedBy())
        );
    }
}
