package com.cesar.school.presentation.dto.projectmanagement.project;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TeamId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProjectRequest {

    public Integer id;

    @NotBlank(message = "O nome do projeto é obrigatório.")
    public String name;

    public String description;

    @NotNull(message = "O ID da equipe é obrigatório.")
    public Integer teamId;

    public Project toDomain() {
        // Se `id` é null, usamos o construtor de criação (sem id)
        if (id == null) {
            return new Project(
                    name,
                    description,
                    new TeamId(teamId)
            );
        }

        // Se `id` não for null, usamos o construtor com id
        return new Project(
                new ProjectId(id),
                name,
                description,
                new TeamId(teamId)
        );
    }
}
