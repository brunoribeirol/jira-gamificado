package com.cesar.school.core.gamification.repository;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;

import java.util.List;
import java.util.Optional;

public interface RewardRepository {

    Reward save(Reward reward);
    Optional<Reward> findById(RewardId rewardId);
    List<Reward> findAll();
    List<Reward> findRewardsAvailableForPoints(int points);
    void deleteById(RewardId rewardId);
}
