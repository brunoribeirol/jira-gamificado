package com.cesar.school.core.projectmanagement.entity;

import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;

import java.util.*;

public class Task {
    private final TaskId id;
    private String title;
    private final String description;
    private final List<MemberId> assignees;
    private final int points;
    private final Date createdAt;
    private String kanbanColumn = "Backlog";
    private Date completedAt;
    private static final List<String> VALID_COLUMNS = List.of("Backlog", "Pronto", "Em Progresso", "Revisão", "Concluído");

    public Task(TaskId id, String title, String description, List<MemberId> assignees, int points) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("O nome da tarefa é obrigatório");
        }

        if (assignees == null || assignees.isEmpty()) {
            throw new IllegalArgumentException("Selecione ao menos um responsável");
        }

        this.id = id;
        this.title = title;
        this.description = description;
        this.assignees = new ArrayList<>(assignees);
        this.points = points;
        this.createdAt = new Date();
    }

    // Novo método para atualizar o título da tarefa
    public void updateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("O nome da tarefa é obrigatório");
        }
        this.title = title;
    }

    public void moveTo(String column) {
        if (!VALID_COLUMNS.contains(column)) {
            throw new IllegalArgumentException("Coluna inválida para movimentação: " + column);
        }
        this.kanbanColumn = column;
    }

    public void complete() {
        this.completedAt = new Date();
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public TaskId getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<MemberId> getAssignees() { return List.copyOf(assignees); }
    public String getKanbanColumn() { return kanbanColumn; }
    public int getPoints() { return points; }
    public Date getCreatedAt() { return createdAt; }
    public Date getCompletedAt() { return completedAt; }

    // Método para atribuir um membro à tarefa
    public void assignTo(MemberId memberId) {
        if (memberId != null) {
            assignees.add(memberId);
        }
    }
}
