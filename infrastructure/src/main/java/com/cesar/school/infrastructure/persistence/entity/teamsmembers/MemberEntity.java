package com.cesar.school.infrastructure.persistence.entity.teamsmembers;

import com.cesar.school.core.teamsmembers.vo.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "individual_score")
    private int individualScore = 0;

    @ElementCollection
    @CollectionTable(name = "member_rewards", joinColumns = @JoinColumn(name = "member_id"))
    private List<RewardIdEmbeddable> unlockedRewards = new ArrayList<>();
}
