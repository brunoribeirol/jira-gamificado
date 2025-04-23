package com.cesar.school.core.projectmanagement;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompleteTaskService {
    private final TaskRepository taskRepository;

    public void execute(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setCompleted(true);
        taskRepository.save(task);
    }
}
