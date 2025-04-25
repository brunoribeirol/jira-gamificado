package com.cesar.school.core.teamsmembers.entity;

import com.cesar.school.core.teamsmembers.vo.MemberId;
import com.cesar.school.core.teamsmembers.vo.TeamId;

import java.util.List;

public class Team {
    private TeamId id;
    private String name;
    private MemberId leader;
    private List<MemberId> members;
    private int teamScore;

    public void addMember(MemberId memberId) {
        if (!members.contains(memberId)) {
            members.add(memberId);
        }
    }

    public void addPoints(int points) {
        this.teamScore += points;
    }
}
