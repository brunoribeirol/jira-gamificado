package com.cesar.school.core.projectmanagement.repository;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;

import java.util.Optional;

public interface ProjectRepository {
    Optional<Project> findById(ProjectId id);
    void save(Project project);
    void deleteById(ProjectId id);
}
