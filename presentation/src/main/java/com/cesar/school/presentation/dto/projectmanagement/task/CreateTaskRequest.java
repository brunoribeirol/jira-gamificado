package com.cesar.school.presentation.dto.projectmanagement.task;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateTaskRequest {

    private Integer id;

    @NotBlank
    private String title;

    private String description;

    private Integer assignedMemberId;

    @Positive
    private int points;

    public Task toDomain() {
        return new Task(
                this.title,
                this.description,
                "Backlog",
                this.points
        );
    }

//    public Task toDomain() {
//        return new Task(
//                title,
//                description,
//                "Backlog",
//                points
//        );
//    }

}