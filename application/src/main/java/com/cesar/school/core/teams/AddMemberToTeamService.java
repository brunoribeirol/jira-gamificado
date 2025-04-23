package com.cesar.school.core.teams;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddMemberToTeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public void execute(Long teamId, Member member) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        team.addMember(member);
        teamRepository.save(team);
    }
}
