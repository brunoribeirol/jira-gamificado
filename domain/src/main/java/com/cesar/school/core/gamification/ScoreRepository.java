package com.cesar.school.core.gamification;

import com.cesar.school.core.teams.Member;

import java.util.List;

public interface ScoreRepository {
    List<Score> findByMember(Member member);
    void save(Score score);
}