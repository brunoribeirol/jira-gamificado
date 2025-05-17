package com.cesar.school.presentation.dto.projectmanagement.task;

import jakarta.validation.constraints.Positive;

public class AssignMemberRequest {
    @Positive
    public int memberId;
}