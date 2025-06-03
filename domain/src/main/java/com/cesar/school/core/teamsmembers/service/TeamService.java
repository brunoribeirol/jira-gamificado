package com.cesar.school.core.teamsmembers.service;

import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.vo.TeamId;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    void addPoints(TeamId teamId, int points);
    Optional<Team> getById(TeamId teamId);
    List<Team> findAll();
}
