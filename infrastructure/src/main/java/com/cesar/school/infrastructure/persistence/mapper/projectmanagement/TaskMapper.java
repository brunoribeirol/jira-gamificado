package com.cesar.school.infrastructure.persistence.mapper.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.infrastructure.persistence.entity.projectmanagement.TaskEntity;

import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();

        if (task.getId() != null) {
            entity.setId(task.getId().getValue());
        }

        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setKanbanColumn(task.getKanbanColumn());
        entity.setPoints(task.getPoints());
        entity.setCreatedAt(task.getCreatedAt());
        entity.setCompletedAt(task.getCompletedAt());

        // 🔥 projectId agora é obrigatório
        entity.setProjectId(task.getProjectId().getValue());

        entity.setAssignees(
                task.getAssignees().stream()
                        .map(MemberId::getValue)
                        .collect(Collectors.toList())
        );

        return entity;
    }

    public static TaskEntity toEntity(Task task, ProjectId projectId) {
        TaskEntity entity = toEntity(task);
        entity.setProjectId(projectId.getValue()); // garante persistência correta caso necessário
        return entity;
    }

    public static Task toDomain(TaskEntity entity) {
        Task task = new Task(
                new TaskId(entity.getId()),
                entity.getTitle(),
                entity.getDescription(),
                entity.getKanbanColumn(),
                entity.getPoints(),
                entity.getCreatedAt(),
                new ProjectId(entity.getProjectId())
        );
        entity.getAssignees().forEach(id -> task.assignTo(new MemberId(id)));
        return task;
    }
}