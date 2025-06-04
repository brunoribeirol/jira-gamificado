package com.cesar.school.core.projectmanagement.repository;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.shared.vo.ProjectId;
import com.cesar.school.core.shared.vo.TaskId;
import com.cesar.school.core.shared.vo.MemberId;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> findById(TaskId id);
    void save(Task task); // usado quando não for necessário projeto
    void save(Task task, ProjectId projectId); // usado com vínculo ao projeto
    void deleteById(TaskId id);
    List<Task> findByProjectId(ProjectId projectId);

    List<Task> findByAssignee(MemberId memberId);
}
