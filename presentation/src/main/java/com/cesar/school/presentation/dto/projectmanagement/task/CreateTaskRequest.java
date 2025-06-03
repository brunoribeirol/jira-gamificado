package com.cesar.school.presentation.dto.projectmanagement.task;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Getter
@Setter
public class CreateTaskRequest {

    private Integer id;

    @NotBlank
    private String title;

    private String description;

    @NotNull(message = "Selecione ao menos um responsável")
    private Integer assignedMemberId;

    @Positive
    private int points;

    public Task toDomain(ProjectId projectId) {
        if (id == null) {
            return new Task(
                    title,
                    description,
                    "Backlog",
                    points,
                    projectId
            );
        } else {
            return new Task(
                    new TaskId(id),
                    title,
                    description,
                    "Backlog",
                    points,
                    new Date(),
                    projectId
            );
        }
    }
}