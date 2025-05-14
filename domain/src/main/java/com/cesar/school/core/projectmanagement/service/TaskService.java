package com.cesar.school.core.projectmanagement.service;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.shared.MemberId;

import java.util.Optional;
import java.util.List;

public interface TaskService {
    void assignTaskToMember(TaskId taskId, MemberId memberId);
    void updateTaskTitle(TaskId taskId, String newTitle);
    void completeTask(TaskId taskId);
    void moveTaskToColumn(TaskId taskId, String column);
    void delete(TaskId taskId);
    void addTaskToProject(ProjectId projectId, Task task);
    Optional<Task> getById(TaskId taskId);
    List<Task> listByProject(ProjectId projectId);
}
