package com.cesar.school.core.gamification.service.impl;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.gamification.service.RewardService;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.shared.MemberId;

import java.util.List;
import java.util.Optional;

public class RewardServiceDomainImpl implements RewardService {

    private final RewardRepository rewardRepository;

    public RewardServiceDomainImpl(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    @Override
    public Reward createReward(RewardId rewardId, String title, String description, int points, MemberId memberId) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Título da recompensa é obrigatório");
        }
        if (points < 0) {
            throw new IllegalArgumentException("Pontos devem ser um valor positivo");
        }

        Reward reward = new Reward(rewardId, title, description, points, memberId);
        rewardRepository.save(reward);
        return reward;
    }

    @Override
    public Optional<Reward> getById(RewardId rewardId) {
        return rewardRepository.findById(rewardId);
    }

    @Override
    public List<Reward> listByMember(MemberId memberId) {
        return rewardRepository.findByMemberId(memberId);
    }

    @Override
    public List<Reward> listAll() {
        return rewardRepository.findAll();
    }

    @Override
    public void delete(RewardId rewardId) {
        rewardRepository.deleteById(rewardId);
    }
}
