package com.cesar.school.application.gamification;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.gamification.service.RewardService;
import com.cesar.school.core.gamification.vo.RewardId;

import java.util.List;
import java.util.Optional;

public class RewardServiceImpl implements RewardService {

    private final RewardRepository rewardRepository;

    public RewardServiceImpl(RewardRepository repository) {
        this.rewardRepository = repository;
    }

    @Override
    public void createReward(Reward reward) {
        if (reward.getRequiredPoints() <= 0) {
            throw new IllegalArgumentException("Required points must be greater than zero.");
        }
        rewardRepository.save(reward);
    }

    @Override
    public Optional<Reward> getRewardById(RewardId rewardId) {
        return rewardRepository.findById(rewardId);
    }

    @Override
    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    @Override
    public List<Reward> getRewardsAvailableForPoints(int points) {
        return rewardRepository.findRewardsAvailableForPoints(points);
    }

    @Override
    public void deleteReward(RewardId rewardId) {
        rewardRepository.deleteById(rewardId);
    }
}
