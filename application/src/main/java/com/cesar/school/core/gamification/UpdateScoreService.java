package com.cesar.school.core.gamification;

import com.cesar.school.core.teams.Member;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class UpdateScoreService {
    private final ScoreRepository scoreRepository;

    public void execute(Member member, int value) {
        Score score = Score.builder().member(member).value(value).dateEarned(LocalDate.now()).build();
        scoreRepository.save(score);
    }
}
