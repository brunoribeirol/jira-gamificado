package com.cesar.school.presentation.dto.teamsmembers;

import jakarta.validation.constraints.Positive;

public class RewardIdRequest {

    @Positive(message = "O ID da recompensa deve ser positivo.")
    public int rewardId;
}
