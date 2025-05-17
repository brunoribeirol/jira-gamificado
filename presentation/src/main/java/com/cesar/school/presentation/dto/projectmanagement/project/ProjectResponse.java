package com.cesar.school.presentation.dto.projectmanagement.project;

import com.cesar.school.core.projectmanagement.entity.Project;
import java.util.List;

public class ProjectResponse {

    public int id;
    public String name;
    public String description;
    public int teamId;
    public List<String> kanbanColumns;

    public static ProjectResponse fromDomain(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.id = project.getId().getValue();
        response.name = project.getName();
        response.description = project.getDescription();
        response.teamId = project.getTeamId().getValue();
        response.kanbanColumns = project.getKanbanColumns();
        return response;
    }
}
