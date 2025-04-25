package com.cesar.school.core.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProjectService {
    private final ProjectRepository projectRepository;

    public void execute(Project project) {
        projectRepository.save(project);
    }
}
