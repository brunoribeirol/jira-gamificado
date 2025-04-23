package com.cesar.school.core.gamification;

import com.cesar.school.core.teams.Member;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class GrantAchievementService {
    private final AchievementRepository achievementRepository;

    public void execute(Member member, String title, String description) {
        Achievement achievement = Achievement.builder()
                .member(member)
                .title(title)
                .description(description)
                .dateAchieved(LocalDate.now())
                .build();
        achievementRepository.save(achievement);
    }
}
