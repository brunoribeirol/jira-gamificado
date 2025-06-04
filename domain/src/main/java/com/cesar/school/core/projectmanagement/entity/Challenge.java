package com.cesar.school.core.projectmanagement.entity;

import com.cesar.school.core.shared.vo.ChallengeId;
import com.cesar.school.core.shared.vo.ProjectId;
import com.cesar.school.core.shared.vo.MemberId;

import java.util.Date;

public class Challenge {
    private final ChallengeId id;
    private final String title;
    private final String description;
    private final String criteria;
    private final int extraPoints;
    private final MemberId createdBy;
    private final ProjectId projectId;
    private final Date deadline;

    // Construtor para feedbacks existentes (com ID)
    public Challenge(ChallengeId id, String title, String description, String criteria, int extraPoints,
                     MemberId createdBy, ProjectId projectId, Date deadline) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Título do desafio é obrigatório");
        }
        if (criteria == null || criteria.isBlank()) {
            throw new IllegalArgumentException("Critério do desafio é obrigatório");
        }
        if (extraPoints < 0) {
            throw new IllegalArgumentException("Pontos extras devem ser positivos");
        }

        this.id = id;
        this.title = title;
        this.description = description;
        this.criteria = criteria;
        this.extraPoints = extraPoints;
        this.createdBy = createdBy;
        this.projectId = projectId;
        this.deadline = deadline;
    }

    // Construtor para criação de novos desafios (sem ID)
    public Challenge(String title, String description, String criteria, int extraPoints,
                     MemberId createdBy, ProjectId projectId, Date deadline) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Título do desafio é obrigatório");
        }
        if (criteria == null || criteria.isBlank()) {
            throw new IllegalArgumentException("Critério do desafio é obrigatório");
        }
        if (extraPoints < 0) {
            throw new IllegalArgumentException("Pontos extras devem ser positivos");
        }
        
        this.id = null; // O ID será gerado pela infraestrutura
        this.title = title;
        this.description = description;
        this.criteria = criteria;
        this.extraPoints = extraPoints;
        this.createdBy = createdBy;
        this.projectId = projectId;
        this.deadline = deadline;
    }

    public ChallengeId getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCriteria() { return criteria; }
    public int getExtraPoints() { return extraPoints; }
    public MemberId getCreatedBy() { return createdBy; }
    public ProjectId getProjectId() { return projectId; }
    public Date getDeadline() { return deadline; }
}
