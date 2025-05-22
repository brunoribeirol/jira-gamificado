package com.cesar.school.application.gamification;

import com.cesar.school.core.gamification.entity.Reward;
<<<<<<< HEAD
import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.gamification.service.RewardService;
import com.cesar.school.core.gamification.vo.RewardId;
=======
import com.cesar.school.core.gamification.service.RewardService;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.shared.MemberId;
>>>>>>> df22e55 (feat: apply professor feedback - refactor services to domain)

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
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
=======
public class RewardServiceImpl {

    private final RewardService rewardService;

    public RewardServiceImpl(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    public Reward createReward(RewardId rewardId, String title, String description, int points, MemberId memberId) {
        return rewardService.createReward(rewardId, title, description, points, memberId);
    }

    public Optional<Reward> getById(RewardId rewardId) {
        return rewardService.getById(rewardId);
    }

    public List<Reward> listByMember(MemberId memberId) {
        return rewardService.listByMember(memberId);
    }

    public List<Reward> listAll() {
        return rewardService.listAll();
    }

    public void delete(RewardId rewardId) {
        rewardService.delete(rewardId);
>>>>>>> df22e55 (feat: apply professor feedback - refactor services to domain)
    }
}
