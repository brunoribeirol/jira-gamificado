package com.cesar.school.core.gamification.vo;

import java.util.Objects;

public final class RewardId {
    private final int value;

    public RewardId(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("RewardId deve ser um número positivo.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RewardId)) return false;
        RewardId rewardId = (RewardId) o;
        return value == rewardId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
