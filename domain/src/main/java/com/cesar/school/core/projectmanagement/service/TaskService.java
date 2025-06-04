package com.cesar.school.core.projectmanagement.service;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.shared.vo.TaskId;
import com.cesar.school.core.shared.vo.ProjectId;
import com.cesar.school.core.shared.vo.MemberId;

import java.util.Optional;
import java.util.List;

public interface TaskService {
    void assignTaskToMember(TaskId taskId, MemberId memberId);
    void updateTaskTitle(TaskId taskId, String newTitle);
    void completeTask(TaskId taskId, MemberId memberId);
    void delete(TaskId taskId);
    void addTaskToProject(ProjectId projectId, Task task, Integer assignedMemberId);
    void moveTaskToColumn(TaskId taskId, String column, MemberId memberId);
    void createTask(Task task);
    Optional<Task> getById(TaskId taskId);
    List<Task> listByProject(ProjectId projectId);
    void unlockTask(TaskId taskId, MemberId memberId);
    List<Task> findByAssignee(MemberId memberId);
}
