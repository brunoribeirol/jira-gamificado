package com.cesar.school.core.gamification.service;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.gamification.vo.RewardId;

import java.util.List;
import java.util.Optional;

public class RewardService {

    private final RewardRepository rewardRepository;

    // Injeção de dependência do repositório de recompensa
    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    // Criação de uma recompensa
    public Reward createReward(Reward reward) {
        // Aqui poderia haver alguma validação antes de salvar a recompensa
        return rewardRepository.save(reward);
    }

    // Recupera uma recompensa pelo seu ID
    public Optional<Reward> getRewardById(RewardId rewardId) {
        return rewardRepository.findById(rewardId);
    }

    // Recupera todas as recompensas
    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    // Recupera as recompensas disponíveis para a quantidade de pontos
    public List<Reward> getRewardsAvailableForPoints(int points) {
        return rewardRepository.findRewardsAvailableForPoints(points);  // Verifique se o método está implementado no repositório
    }

    // Excluir uma recompensa usando seu ID
    public void deleteReward(RewardId rewardId) {
        rewardRepository.deleteById(rewardId);  // Verifique se o método correto de exclusão é usado
    }
}
