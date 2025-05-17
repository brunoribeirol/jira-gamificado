package com.cesar.school.infrastructure.persistence.mapper.teamsmembers;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.vo.TeamId;
import com.cesar.school.infrastructure.persistence.entity.teamsmembers.TeamEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    public static TeamEntity toEntity(Team team) {
        List<Integer> memberIds = team.getMembers().stream()
                .map(MemberId::getValue)
                .collect(Collectors.toList());

        return new TeamEntity(
                team.getId().getValue(),
                team.getName(),
                team.getLeaderId().getValue(),
                memberIds,
                team.getTeamScore()
        );
    }

    public static Team toDomain(TeamEntity entity) {
        List<MemberId> members = entity.getMembers().stream()
                .map(MemberId::new)
                .collect(Collectors.toList());

        return new Team(
                new TeamId(entity.getId()),
                entity.getName(),
                new MemberId(entity.getLeaderId()),
                members
        );
    }
}