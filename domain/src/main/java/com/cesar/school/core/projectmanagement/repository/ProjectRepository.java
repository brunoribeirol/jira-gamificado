package com.cesar.school.core.projectmanagement.repository;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Project save(Project project);
    Optional<Project> findById(ProjectId id);
    List<Project> findAll();
    void deleteById(ProjectId id);
}
