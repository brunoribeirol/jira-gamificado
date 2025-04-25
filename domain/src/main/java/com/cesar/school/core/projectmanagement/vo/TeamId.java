package com.cesar.school.core.projectmanagement.vo;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.springframework.util.Assert.isTrue;

import java.util.Objects;

class TeamId {
    private final int value;

    public TeamId(int value) {
        isTrue(value > 0, "O ID do time deve ser positivo");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TeamId that = (TeamId) obj;
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
