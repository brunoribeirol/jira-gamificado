package com.cesar.school.presentation.dto.gamification;

import jakarta.validation.constraints.Min;

public class UpdateRewardPointsRequest {

    @Min(value = 1, message = "A pontuação mínima deve ser maior que zero.")
    public int requiredPoints;
}