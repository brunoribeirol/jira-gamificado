package com.cesar.school.core.projectmanagement;

import com.cesar.school.core.teams.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed;

    @ManyToOne
    private Stage stage;

    @ManyToOne
    private Project project;

    @ManyToMany
    private List<Member> assignees;

    private int scoreValue;
}
