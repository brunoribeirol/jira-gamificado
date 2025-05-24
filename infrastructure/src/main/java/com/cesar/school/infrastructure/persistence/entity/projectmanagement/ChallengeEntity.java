package com.cesar.school.infrastructure.persistence.entity.projectmanagement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "challenges")
public class ChallengeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String criteria;

    @Column(name = "extra_points")
    private int extraPoints;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "project_id")
    private int projectId;

    private Date deadline;

    public ChallengeEntity(int id, String title, String description, String criteria, int extraPoints,
                           int createdBy, int projectId, Date deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.criteria = criteria;
        this.extraPoints = extraPoints;
        this.createdBy = createdBy;
        this.projectId = projectId;
        this.deadline = deadline;
    }
}
