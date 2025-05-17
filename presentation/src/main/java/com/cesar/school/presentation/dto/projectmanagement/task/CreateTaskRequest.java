package com.cesar.school.presentation.dto.projectmanagement.task;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public class CreateTaskRequest {

    @Positive
    public int id;

    @NotBlank
    public String title;

    public String description;

    @NotBlank
    public String kanbanColumn;

    @Positive
    public int points;

    public Task toDomain() {
        return new Task(
                new TaskId(id),
                title,
                description,
                kanbanColumn,
                points,
                new Date()
        );
    }
}