package com.cesar.school.core.teamsmembers.service;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.vo.Role;
import com.cesar.school.core.teamsmembers.vo.TeamId;
import com.cesar.school.core.gamification.vo.RewardId;

import java.util.List;
import java.util.Optional;

public interface MemberTeamService {

    void createTeam(TeamId teamId, String teamName);
    void addMember(TeamId teamId, MemberId memberId, Role role);
    List<RewardId> getUnlockedRewards(MemberId memberId);
    int getScore(MemberId memberId);
    List<Feedback> getReceivedFeedbacks(MemberId memberId);
    void createMember(Member member);
    List<Member> findMembersByTeamId(TeamId teamId);
    Optional<Team> getById(TeamId teamId);
    List<Team> findAll();
    void addPoints(TeamId teamId, int points);
    boolean isTeamLeader(MemberId memberId, TeamId teamId);

}
