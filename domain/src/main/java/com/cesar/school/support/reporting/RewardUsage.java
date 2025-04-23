package com.cesar.school.support.reporting;

import com.cesar.school.core.gamification.Reward;
import com.cesar.school.core.teams.Member;

import java.time.LocalDate;
import java.util.List;

public class RewardUsage {
    private Member member;
    private List<Reward> rewardsUsed;
    private LocalDate period;
}
