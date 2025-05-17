package com.cesar.school.presentation.dto.gamification;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.gamification.vo.RewardType;
import com.cesar.school.core.shared.MemberId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateRewardRequest {

    @NotNull
    public Integer id;

    @NotBlank
    public String description;

    @Min(value = 1, message = "A pontuação mínima deve ser maior que zero.")
    public int requiredPoints;

    @NotNull
    public RewardType type;

    @NotNull
    public Integer createdBy;

    public Reward toDomain() {
        return new Reward(
                new RewardId(id),
                description,
                requiredPoints,
                type,
                new MemberId(createdBy)
        );
    }
}
