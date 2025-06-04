package com.cesar.school.core.projectmanagement.service;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.shared.vo.ProjectId;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(Project project);
    Optional<Project> getById(ProjectId id);
    List<Project> getAll();
    void update(Project project);
    void delete(ProjectId id);
    void addTaskToProject(ProjectId projectId, Task task, Integer assignedMemberId);
}
