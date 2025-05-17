package com.cesar.school.presentation.dto.projectmanagement.task;

import jakarta.validation.constraints.NotBlank;

public class MoveTaskRequest {
    @NotBlank
    public String column;
}