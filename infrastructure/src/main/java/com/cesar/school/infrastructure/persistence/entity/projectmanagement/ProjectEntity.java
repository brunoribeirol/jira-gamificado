package com.cesar.school.infrastructure.persistence.entity.projectmanagement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @Column(name = "team_id")
    private int teamId;

    public ProjectEntity(Integer id, String name, String description, int teamId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teamId = teamId;
    }

    public ProjectEntity(String name, String description, int teamId) {
        this.name = name;
        this.description = description;
        this.teamId = teamId;
    }

}
