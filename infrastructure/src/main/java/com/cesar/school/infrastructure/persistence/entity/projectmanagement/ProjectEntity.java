package com.cesar.school.infrastructure.persistence.entity.projectmanagement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "projects")
public class ProjectEntity {

    @Id
    private int id;

    private String name;
    private String description;

    @Column(name = "team_id")
    private int teamId;

    @ElementCollection
    @CollectionTable(name = "project_kanban_columns", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "column_name")
    private List<String> kanbanColumns;

    public ProjectEntity() {
    }

    public ProjectEntity(int id, String name, String description, int teamId, List<String> kanbanColumns) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teamId = teamId;
        this.kanbanColumns = kanbanColumns;
    }

}
