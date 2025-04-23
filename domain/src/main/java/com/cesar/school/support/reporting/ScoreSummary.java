package com.cesar.school.support.reporting;

import com.cesar.school.core.projectmanagement.Project;
import com.cesar.school.core.teams.Member;

import java.util.Map;

public class ScoreSummary {
    private Project project;
    private int totalTeamScore;
    private Map<Member, Integer> memberScores;
}
