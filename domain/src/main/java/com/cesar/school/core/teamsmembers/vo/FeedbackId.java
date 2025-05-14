package com.cesar.school.core.teamsmembers.vo;

import java.util.Objects;

public final class FeedbackId {
    private final int value;

    public FeedbackId(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("O ID do feedback deve ser positivo");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedbackId)) return false;
        FeedbackId that = (FeedbackId) o;
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
