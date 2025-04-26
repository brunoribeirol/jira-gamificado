package com.cesar.school.core.projectmanagement.vo;

import java.util.Objects;

public class ChallengeId {
    private final int value;

    public ChallengeId(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("O ID do desafio deve ser positivo");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChallengeId that = (ChallengeId) obj;
        return value == that.value;
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
