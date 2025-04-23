package com.cesar.school.core.projectmanagement;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String criteria;
    private int scoreReward;

    @ManyToOne
    private Stage stage;

    private boolean prerequisiteMet;
}
