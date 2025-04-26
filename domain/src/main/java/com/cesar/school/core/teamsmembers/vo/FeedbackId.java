package com.cesar.school.core.teamsmembers.vo;

import java.util.Objects;

public class FeedbackId {
    private final int value;

    public FeedbackId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedbackId)) return false;
        FeedbackId feedbackId = (FeedbackId) o;
        return value == feedbackId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
