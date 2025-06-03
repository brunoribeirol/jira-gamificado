package com.cesar.school.core.teamsmembers.repository;

import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.vo.TeamId;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
    void save(Team team);
    Optional<Team> findById(TeamId teamId);
    void delete(Team team);
    List<Team> findAll();
}
