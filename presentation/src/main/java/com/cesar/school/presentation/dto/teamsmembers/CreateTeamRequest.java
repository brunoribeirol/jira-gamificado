package com.cesar.school.presentation.dto.teamsmembers;

import com.cesar.school.core.shared.vo.MemberId;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.shared.vo.TeamId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class CreateTeamRequest {

    @Positive
    public int id;

    @NotBlank
    public String name;
    @Positive
    public int leaderId;

    public Team toDomain() {
        MemberId leader = new MemberId(leaderId);
        return new Team(
                new TeamId(id),
                name,
                leader,
                List.of(leader)
        );
    }

}
