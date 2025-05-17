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

    @ElementCollection
    @CollectionTable(name = "team_members", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "member_id")
    private List<Integer> members;

    @Column(name = "team_score")
    private int teamScore;

    public TeamEntity(int id, String name, int leaderId, List<Integer> members, int teamScore) {
        this.id = id;
        this.name = name;
        this.leaderId = leaderId;
        this.members = members;
        this.teamScore = teamScore;
    }
}