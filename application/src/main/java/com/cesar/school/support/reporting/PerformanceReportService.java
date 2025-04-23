package com.cesar.school.support.reporting;

import com.cesar.school.core.gamification.Score;
import com.cesar.school.core.gamification.ScoreRepository;
import com.cesar.school.core.teams.Feedback;
import com.cesar.school.core.teams.FeedbackRepository;
import com.cesar.school.core.teams.Member;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PerformanceReportService {

    private final ScoreRepository    scoreRepository;
    private final FeedbackRepository feedbackRepository;

    public PerformanceReport generate(Member member) {

        int totalScore = scoreRepository.findByMember(member)
                .stream()
                .mapToInt(Score::getValue)
                .sum();

        int tasksCompleted   = 0;
        int rewardsUnlocked  = 0;

        List<Feedback> feedbacks = feedbackRepository.findByRecipient(member);

        return new PerformanceReport(member, totalScore, tasksCompleted, rewardsUnlocked, feedbacks);
    }
}
