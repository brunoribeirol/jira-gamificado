package com.cesar.school.support.reporting;

import com.cesar.school.core.teams.Feedback;
import com.cesar.school.core.teams.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class PerformanceReport {
    private Member member;
    private int totalScore;
    private int tasksCompleted;
    private int challengesCompleted;
    private List<Feedback> feedbacks;
}
