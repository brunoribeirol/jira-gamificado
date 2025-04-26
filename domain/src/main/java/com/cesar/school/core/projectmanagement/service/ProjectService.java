package com.cesar.school.core.projectmanagement.service;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TeamId;

import java.util.Optional;

public interface ProjectService {
    Project create(ProjectId id, String name, String description, TeamId teamId);
    Optional<Project> getById(ProjectId id);
    void update(Project project);
    void delete(ProjectId id);
}
