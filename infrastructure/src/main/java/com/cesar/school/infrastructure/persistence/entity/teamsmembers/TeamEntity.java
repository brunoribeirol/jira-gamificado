package com.cesar.school.infrastructure.persistence.entity.teamsmembers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
public class TeamEntity {

    @Id
    private int id;

    private String name;

    @Column(name = "leader_id")
    private int leaderId;

    @Column(name = "team_score")
    private int teamScore;

    public TeamEntity(int id, String name, int leaderId, int teamScore) {
        this.id = id;
        this.name = name;
        this.leaderId = leaderId;
        this.teamScore = teamScore;
    }
}