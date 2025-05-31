package com.cesar.school.presentation.dto.projectmanagement.task;

import com.cesar.school.core.projectmanagement.entity.Task;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskResponse {
    public int id;
    public String title;
    public String description;
    public String kanbanColumn;
    public int points;
    public Date createdAt;
    public Date completedAt;
    public List<Integer> assignees;
    public int projectId; // 🟢 novo campo

    public static TaskResponse fromDomain(Task task) {
        TaskResponse response = new TaskResponse();
        response.id = task.getId().getValue();
        response.title = task.getTitle();
        response.description = task.getDescription();
        response.kanbanColumn = task.getKanbanColumn();
        response.points = task.getPoints();
        response.createdAt = task.getCreatedAt();
        response.completedAt = task.getCompletedAt();
        response.assignees = task.getAssignees().stream().map(a -> a.getValue()).collect(Collectors.toList());
        response.projectId = task.getProjectId().getValue(); // 🟢 adiciona vínculo no response
        return response;
    }
}
