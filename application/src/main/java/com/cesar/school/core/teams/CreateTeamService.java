package com.cesar.school.core.teams;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTeamService {
    private final TeamRepository teamRepository;

    public void execute(String teamName) {
        Team team = Team.builder().name(teamName).build();
        teamRepository.save(team);
    }
}
