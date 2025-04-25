package com.cesar.school.core.gamification;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.teams.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Project project;

    private LocalDate dateEarned;

    public int getValue() {
        return value;
    }
}
