package com.cesar.school.core.gamification;

import com.cesar.school.core.teams.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int requiredScore;
    private boolean unlocked;

    @ManyToOne
    private Member member;
}
