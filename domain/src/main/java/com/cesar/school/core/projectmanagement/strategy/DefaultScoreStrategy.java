package com.cesar.school.core.projectmanagement.strategy;

public class DefaultScoreStrategy implements TaskScoreStrategy {
    @Override
    public int calculatePoints(int basePoints) {
        return basePoints;
    }
}