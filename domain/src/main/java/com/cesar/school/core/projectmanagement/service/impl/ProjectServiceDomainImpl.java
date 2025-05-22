package com.cesar.school.core.projectmanagement.service.impl;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.service.ProjectService;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TeamId;

import java.util.Optional;

public class ProjectServiceDomainImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceDomainImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(ProjectId projectId, String name, String description, TeamId teamId) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do projeto é obrigatório");
        }

        Project project = new Project(projectId, name, description, teamId);
        projectRepository.save(project);
        return project;
    }

    @Override
    public Optional<Project> getById(ProjectId id) {
        return projectRepository.findById(id);
    }

    @Override
    public void update(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void delete(ProjectId id) {
        projectRepository.deleteById(id);
    }
}
