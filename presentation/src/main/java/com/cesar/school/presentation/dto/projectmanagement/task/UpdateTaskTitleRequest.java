package com.cesar.school.presentation.dto.projectmanagement.task;

import jakarta.validation.constraints.NotBlank;

public class UpdateTaskTitleRequest {
    @NotBlank
    public String newTitle;
}