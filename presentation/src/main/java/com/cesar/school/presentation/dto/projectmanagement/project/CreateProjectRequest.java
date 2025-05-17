package com.cesar.school.presentation.dto.projectmanagement.project;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TeamId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateProjectRequest {

    @Positive(message = "O ID do projeto deve ser positivo.")
    public int id;

    @NotBlank(message = "O nome é obrigatório.")
    public String name;

    public String description;

    @Positive(message = "O ID da equipe deve ser positivo.")
    public int teamId;

    public Project toDomain() {
        return new Project(
                new ProjectId(id),
                name,
                description,
                new TeamId(teamId)
        );
    }
}
