package com.cesar.school.core.teamsmembers.service;

import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.shared.vo.MemberId;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    void addPoints(MemberId memberId, int points);
    void addFeedback(MemberId memberId, Feedback feedback);
    void unlockReward(MemberId memberId, RewardId rewardId);
    void createMember(Member member);
    Optional<Member> getById(MemberId memberId);
    List<Member> getAll();
}
