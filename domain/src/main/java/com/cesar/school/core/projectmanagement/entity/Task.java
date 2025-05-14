package com.cesar.school.core.projectmanagement.entity;

import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Task {
    private final TaskId id;
    private String title; // ❗ Modificável
    private final String description;
    private final List<MemberId> assignees;
    private String kanbanColumn;
    private int points;
    private final Date createdAt;
    private Date completedAt;

    public Task(TaskId id, String title, String description, String kanbanColumn, int points, Date createdAt) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("O nome da tarefa é obrigatório");
        }
        if (points < 0) {
            throw new IllegalArgumentException("A pontuação deve ser zero ou positiva");
        }
        this.id = Objects.requireNonNull(id);
        this.title = title;
        this.description = description;
        this.kanbanColumn = Objects.requireNonNull(kanbanColumn);
        this.points = points;
        this.createdAt = new Date(createdAt.getTime());
        this.assignees = new ArrayList<>();
        this.completedAt = null;
    }

    // ==== Regras de Negócio ====

    public void assignTo(MemberId memberId) {
        if (!assignees.contains(memberId)) {
            assignees.add(memberId);
        }
    }

    public void moveToColumn(String column) {
        if (column == null || column.isBlank()) {
            throw new IllegalArgumentException("A coluna não pode ser vazia");
        }
        this.kanbanColumn = column;
    }

    public void setPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("A pontuação deve ser zero ou positiva");
        }
        this.points = points;
    }

    public void markAsCompleted() {
        this.completedAt = new Date();
        moveToColumn("Concluído");
    }

    public void setTitle(String newTitle) {
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da tarefa é obrigatório");
        }
        this.title = newTitle;
    }

    // ==== Getters ====

    public TaskId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<MemberId> getAssignees() {
        return Collections.unmodifiableList(assignees);
    }

    public String getKanbanColumn() {
        return kanbanColumn;
    }

    public int getPoints() {
        return points;
    }

    public Date getCreatedAt() {
        return new Date(createdAt.getTime());
    }

    public Date getCompletedAt() {
        return completedAt != null ? new Date(completedAt.getTime()) : null;
    }

    public boolean isCompleted() {
        return completedAt != null;
    }
}
