package com.cesar.school.infrastructure.persistence.repository.teamsmembers;

import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.repository.TeamRepository;
import com.cesar.school.core.teamsmembers.vo.TeamId;
import com.cesar.school.infrastructure.persistence.entity.teamsmembers.TeamEntity;
import com.cesar.school.infrastructure.persistence.mapper.teamsmembers.TeamMapper;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.TeamJpaRepository;

import java.util.Optional;

public class TeamRepositoryImpl implements TeamRepository {

    private final TeamJpaRepository jpaRepository;

    public TeamRepositoryImpl(TeamJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Team team) {
        TeamEntity entity = TeamMapper.toEntity(team);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Team> findById(TeamId teamId) {
        return jpaRepository.findById(teamId.getValue())
                .map(TeamMapper::toDomain);
    }

    @Override
    public void delete(Team team) {
        jpaRepository.deleteById(team.getId().getValue());
    }
}