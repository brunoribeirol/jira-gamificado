package com.cesar.school.infrastructure.persistence.entity.projectmanagement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class TaskEntity {
    @Id
    private int id;

    private String title;
    private String description;

    @ElementCollection
    @CollectionTable(name = "task_assignees", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "member_id")
    private List<Integer> assignees;

    private String kanbanColumn;
    private int points;
    private Date createdAt;
    private Date completedAt;
}
