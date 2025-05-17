package com.cesar.school.infrastructure.persistence.springdata.gamification;

import com.cesar.school.infrastructure.persistence.entity.gamification.RewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardJpaRepository extends JpaRepository<RewardEntity, Integer> {
    List<RewardEntity> findByRequiredPointsLessThanEqual(int points);
}
