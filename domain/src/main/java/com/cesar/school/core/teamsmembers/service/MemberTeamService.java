package com.cesar.school.core.teamsmembers.service;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.shared.TeamId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.vo.Role;
import com.cesar.school.core.gamification.vo.RewardId;

import java.util.List;

public interface MemberTeamService {

    void createTeam(Team team);
    void addMember(TeamId teamId, MemberId memberId, Role role);
    List<RewardId> getUnlockedRewards(MemberId memberId);
    int getScore(MemberId memberId);
    List<Feedback> getReceivedFeedbacks(MemberId memberId);
    void createMember(Member member);

}
