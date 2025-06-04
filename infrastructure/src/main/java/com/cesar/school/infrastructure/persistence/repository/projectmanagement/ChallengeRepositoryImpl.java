package com.cesar.school.infrastructure.persistence.repository.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.repository.ChallengeRepository;
import com.cesar.school.core.shared.vo.ChallengeId;
import com.cesar.school.core.shared.vo.ProjectId;
import com.cesar.school.infrastructure.persistence.entity.projectmanagement.ChallengeEntity;
import com.cesar.school.infrastructure.persistence.mapper.projectmanagement.ChallengeMapper;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.ChallengeJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChallengeRepositoryImpl implements ChallengeRepository {

    private final ChallengeJpaRepository jpaRepository;

    public ChallengeRepositoryImpl(ChallengeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Challenge> findById(ChallengeId id) {
        return jpaRepository.findById(id.getValue())
                .map(ChallengeMapper::toDomain);
    }

    @Override
    public void save(Challenge challenge) {
        ChallengeEntity entity = ChallengeMapper.toEntity(challenge);
        jpaRepository.save(entity);
    }

    @Override
    public void deleteById(ChallengeId id) {
        jpaRepository.deleteById(id.getValue());
    }

    @Override
    public List<Challenge> findAllByProjectId(ProjectId projectId) {
        return jpaRepository
                .findAllByProjectId(projectId.getValue())
                .stream()
                .map(ChallengeMapper::toDomain)
                .collect(Collectors.toList());
    }
}
