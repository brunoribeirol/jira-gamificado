package com.cesar.school.infrastructure.persistence.repository.gamification;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.infrastructure.persistence.entity.gamification.RewardEntity;
import com.cesar.school.infrastructure.persistence.mapper.gamification.RewardMapper;
import com.cesar.school.infrastructure.persistence.springdata.gamification.RewardJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RewardRepositoryImpl implements RewardRepository {

    private final RewardJpaRepository jpaRepository;

    public RewardRepositoryImpl(RewardJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Reward reward) {
        RewardEntity entity = RewardMapper.toEntity(reward);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Reward> findById(RewardId rewardId) {
        return jpaRepository.findById(rewardId.getValue())
                .map(RewardMapper::toDomain);
    }

    @Override
    public List<Reward> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(RewardMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reward> findRewardsAvailableForPoints(int points) {
        return jpaRepository.findByRequiredPointsLessThanEqual(points)
                .stream()
                .map(RewardMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(RewardId rewardId) {
        jpaRepository.deleteById(rewardId.getValue());
    }
}
