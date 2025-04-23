package com.cesar.school.core.gamification;

import com.cesar.school.core.teams.Member;
import com.cesar.school.core.teams.Team;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // TEAM or MEMBER
    private int position;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Team team;

    private LocalDate rankingDate;
}
