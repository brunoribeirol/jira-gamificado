package com.cesar.school.core.gamification.service;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;

import java.util.List;
import java.util.Optional;

public interface RewardService {

    void createReward(Reward reward);
    Optional<Reward> getRewardById(RewardId rewardId);
    List<Reward> getAllRewards();
    List<Reward> getRewardsAvailableForPoints(int points);
    void deleteReward(RewardId rewardId);
}
