package com.cesar.school.presentation.dto.teamsmembers;

import com.cesar.school.core.teamsmembers.entity.Team;

import java.util.List;

public class TeamResponse {

    public int id;
    public String name;
    public int leaderId;
    public List<Integer> memberIds;
    public int teamScore;

    public static TeamResponse fromDomain(Team team) {
        TeamResponse response = new TeamResponse();
        response.id = team.getId().getValue();
        response.name = team.getName();
        response.leaderId = team.getLeaderId().getValue();
        response.teamScore = team.getTeamScore();
        return response;
    }
}