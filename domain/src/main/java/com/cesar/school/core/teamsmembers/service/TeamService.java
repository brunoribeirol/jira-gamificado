package com.cesar.school.core.teamsmembers.service;

import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.vo.TeamId;

public interface TeamService {
    void saveTeam(Team team);
    Team getTeamById(TeamId teamId);
    void updateTeam(Team team);
    void deleteTeam(Team team);
}
