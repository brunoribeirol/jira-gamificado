package com.cesar.school.core.projectmanagement.strategy;

public class BonusForPairProgrammingStrategy implements TaskScoreStrategy {
    private final int bonus;

    public BonusForPairProgrammingStrategy(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public int calculatePoints(int basePoints) {
        return basePoints + bonus;
    }
}
