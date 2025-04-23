package com.cesar.school.core.projectmanagement;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProjectService {
    private final ProjectRepository projectRepository;

    public void execute(Project project) {
        projectRepository.save(project);
    }
}
