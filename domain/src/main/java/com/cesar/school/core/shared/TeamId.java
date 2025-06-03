package com.cesar.school.core.shared;

import java.util.Objects;

public class TeamId {

    private final int value;

    public TeamId(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("TeamId deve ser positivo");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamId)) return false;
        TeamId that = (TeamId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
