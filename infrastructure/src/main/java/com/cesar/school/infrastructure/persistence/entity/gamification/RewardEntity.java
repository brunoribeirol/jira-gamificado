package com.cesar.school.infrastructure.persistence.entity.gamification;

import com.cesar.school.core.gamification.vo.RewardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rewards")
@Getter
@Setter
@NoArgsConstructor
public class RewardEntity {

    @Id
    private int id;

    private String description;

    @Column(name = "required_points")
    private int requiredPoints;

    @Enumerated(EnumType.STRING)
    private RewardType type;

    @Column(name = "created_by")
    private int createdBy;

    public RewardEntity(int id, String description, int requiredPoints, RewardType type, int createdBy) {
        this.id = id;
        this.description = description;
        this.requiredPoints = requiredPoints;
        this.type = type;
        this.createdBy = createdBy;
    }
}
