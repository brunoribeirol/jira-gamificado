package com.cesar.school.infrastructure.persistence.mapper.teamsmembers;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.shared.TeamId;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.infrastructure.persistence.entity.teamsmembers.TeamEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    public static TeamEntity toEntity(Team team) {
        TeamEntity entity = new TeamEntity(
                team.getId().getValue(),
                team.getName(),
                team.getLeaderId().getValue(),
                team.getTeamScore()
        );

        // Adiciona os memberIds (se estiver persistindo)
        entity.setMemberIds(
                team.getMembers().stream()
                        .map(MemberId::getValue)
                        .collect(Collectors.toList())
        );

        return entity;
    }

    public static Team toDomain(TeamEntity entity) {
        List<MemberId> memberIds = entity.getMemberIds() != null
                ? entity.getMemberIds().stream()
                .map(MemberId::new)
                .collect(Collectors.toList())
                : List.of(); // caso seja nulo

        Team team = new Team(
                new TeamId(entity.getId()),
                entity.getName(),
                new MemberId(entity.getLeaderId()),
                memberIds
        );

        team.addPoints(entity.getTeamScore());

        return team;
    }
}