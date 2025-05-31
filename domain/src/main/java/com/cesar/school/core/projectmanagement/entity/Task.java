package com.cesar.school.core.projectmanagement.entity;

import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.projectmanagement.strategy.TaskScoreStrategy;

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
    private final ProjectId projectId;


    public Task(TaskId id, String title, String description, String kanbanColumn, int points, Date createdAt, ProjectId projectId) {
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
        this.projectId = Objects.requireNonNull(projectId);
    }

    // Construtor para criação de novas tarefas
    public Task(String title, String description, String kanbanColumn, int points, ProjectId projectId) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("O nome da tarefa é obrigatório");
        }
        if (points < 0) {
            throw new IllegalArgumentException("A pontuação deve ser zero ou positiva");
        }
        this.id = null; // será preenchido pela infraestrutura
        this.title = title;
        this.description = description;
        this.kanbanColumn = Objects.requireNonNull(kanbanColumn);
        this.points = points;
        this.createdAt = new Date(); // agora() no momento da criação
        this.assignees = new ArrayList<>();
        this.completedAt = null;
        this.projectId = Objects.requireNonNull(projectId);
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

    public void initializePoints(TaskScoreStrategy strategy) {
        Objects.requireNonNull(strategy, "A estratégia de pontuação não pode ser nula");
        int pontosCalculados = strategy.calculatePoints(this.points);
        setPoints(pontosCalculados); // já valida pontos >= 0
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

    public void unlockBy(Member member) {
        Objects.requireNonNull(member, "O membro não pode ser nulo");
        member.spendPoints(this.points); // essa validação fica no Member
        assignTo(member.getId());        // tarefa registra quem a desbloqueou
    }

    public void complete(TaskScoreStrategy strategy) {
        Objects.requireNonNull(strategy, "Estratégia de pontuação não pode ser nula");
        int pontosCalculados = strategy.calculatePoints(this.points);
        setPoints(pontosCalculados); // reutiliza validação existente
        markAsCompleted();
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

    public ProjectId getProjectId() {
        return projectId;
    }

    public boolean isCompleted() {
        return completedAt != null;
    }
}
