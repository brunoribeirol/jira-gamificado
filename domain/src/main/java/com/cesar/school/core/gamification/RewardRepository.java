package com.cesar.school.core.gamification;

import com.cesar.school.core.teams.Member;

import java.util.List;

public interface RewardRepository {
    List<Reward> findByMember(Member member);
    void save(Reward reward);
}