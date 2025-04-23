package com.cesar.school.core.projectmanagement;

import java.util.Optional;

public interface ProjectRepository {
    Optional<Project> findById(Long id);
    void save(Project project);
}