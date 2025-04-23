package com.cesar.school.core.teams;

import com.cesar.school.core.gamification.Score;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private boolean leader;

    @ManyToOne
    @JoinColumn(name = "team_id") // was: equipe_id
    private Team team;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Feedback> receivedFeedbacks;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Feedback> givenFeedbacks;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Score> scores;

    public void promoteToLeader() {
        this.leader = true;
    }

    public int getTotalScore() {
        return scores.stream().mapToInt(Score::getValue).sum();
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
