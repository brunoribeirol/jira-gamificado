package com.cesar.school.core.projectmanagement.entity;

import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.shared.TeamId;

import java.util.*;

public class Project implements Iterable<Task> {
    private final ProjectId id;
    private final String name;
    private final String description;
    private final TeamId teamId;
    private final List<Task> tasks = new ArrayList<>();
    private final List<Challenge> challenges = new ArrayList<>();
    private final List<String> kanbanColumns = List.of("Backlog", "Pronto", "Em Progresso", "Revisão", "Concluído");

    public Project(ProjectId id, String name, String description, TeamId teamId) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.description = description; // description pode ser opcional
        this.teamId = Objects.requireNonNull(teamId, "teamId must not be null");
    }

    // Usado para criar novo Project (sem id ainda)
    public Project(String name, String description, TeamId teamId) {
        this.id = null;
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.description = description;
        this.teamId = Objects.requireNonNull(teamId, "teamId must not be null");
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Tarefa não pode ser nula");
        }
        this.tasks.add(task);
    }

    public void addChallenge(Challenge challenge) {
        if (challenge == null) {
            throw new IllegalArgumentException("Desafio não pode ser nulo");
        }
        this.challenges.add(challenge);
    }


    public ProjectId getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public TeamId getTeamId() { return teamId; }
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
    public List<Task> getTasks() { return List.copyOf(tasks); }
    public List<Challenge> getChallenges() { return List.copyOf(challenges); }
    public List<String> getKanbanColumns() {
        return Collections.unmodifiableList(kanbanColumns);
    }

}
