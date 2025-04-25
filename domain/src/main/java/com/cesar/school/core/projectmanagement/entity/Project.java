package com.cesar.school.core.projectmanagement.entity;

import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TeamId;

import java.util.*;

public class Project {
    private final ProjectId id;
    private final String name;
    private final String description;
    private final TeamId teamId;
    private final List<Task> tasks = new ArrayList<>();
    private final List<Challenge> challenges = new ArrayList<>();
    private final List<String> kanbanColumns = List.of("Backlog", "Pronto", "Em Progresso", "Revisão", "Concluído");

    public Project(ProjectId id, String name, String description, TeamId teamId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teamId = teamId;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addChallenge(Challenge challenge) {
        this.challenges.add(challenge);
    }

    public ProjectId getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public TeamId getTeamId() { return teamId; }
    public List<Task> getTasks() { return List.copyOf(tasks); }
    public List<Challenge> getChallenges() { return List.copyOf(challenges); }
    public List<String> getKanbanColumns() { return kanbanColumns; }
}
