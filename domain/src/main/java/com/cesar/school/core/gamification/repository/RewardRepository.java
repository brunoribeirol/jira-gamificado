package com.cesar.school.core.gamification.repository;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;

import java.util.List;
import java.util.Optional;

public interface RewardRepository {
    void save(Reward reward);
    Optional<Reward> findById(RewardId id);
    List<Reward> findAll();
    List<Reward> findRewardsAvailableFor(int score);
}
