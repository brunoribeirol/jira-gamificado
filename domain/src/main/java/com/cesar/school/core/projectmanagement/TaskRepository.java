package com.cesar.school.core.projectmanagement;

import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(Long id);
    void save(Task task);
}