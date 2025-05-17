package com.cesar.school.infrastructure.persistence.mapper.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TeamId;
import com.cesar.school.infrastructure.persistence.entity.projectmanagement.ProjectEntity;

public class ProjectMapper {

    public static ProjectEntity toEntity(Project domain) {
        return new ProjectEntity(
                domain.getId().getValue(),
                domain.getName(),
                domain.getDescription(),
                domain.getTeamId().getValue(),
                domain.getKanbanColumns()
        );
    }

    public static Project toDomain(ProjectEntity entity) {
        return new Project(
                new ProjectId(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                new TeamId(entity.getTeamId())
        );
    }
}
