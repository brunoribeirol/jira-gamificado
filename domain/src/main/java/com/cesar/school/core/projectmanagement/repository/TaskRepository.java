package com.cesar.school.core.projectmanagement.repository;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.TaskId;

import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(TaskId id);
    void save(Task task);
    void deleteById(TaskId id);
}
