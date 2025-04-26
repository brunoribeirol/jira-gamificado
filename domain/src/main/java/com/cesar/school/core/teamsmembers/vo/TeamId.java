package com.cesar.school.core.teamsmembers.vo;

import java.util.Objects;

public class TeamId {
    private final int value;

    public TeamId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamId)) return false;
        TeamId teamId = (TeamId) o;
        return value == teamId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
