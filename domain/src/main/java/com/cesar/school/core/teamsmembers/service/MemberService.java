package com.cesar.school.core.teamsmembers.service;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.shared.MemberId;

import java.util.Optional;

public interface MemberService {

    void addPoints(MemberId memberId, int points);
    void addFeedback(MemberId memberId, Feedback feedback);
    void unlockReward(MemberId memberId, Reward reward);
    void createMember(Member member);
    Optional<Member> getById(MemberId memberId);
}
