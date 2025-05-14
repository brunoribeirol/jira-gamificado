package com.cesar.school.core.shared;

import java.util.Objects;

public final class MemberId {
    private final int value;

    public MemberId(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("MemberId deve ser um número positivo.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberId)) return false;
        MemberId memberId = (MemberId) o;
        return value == memberId.value;
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
