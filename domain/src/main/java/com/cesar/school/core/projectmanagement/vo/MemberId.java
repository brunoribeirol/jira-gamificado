package com.cesar.school.core.projectmanagement.vo;

import java.util.Objects;

import static org.springframework.util.Assert.isTrue;

public class MemberId {
    private final int value;

    public MemberId(int value) {
        isTrue(value > 0, "O ID do membro deve ser positivo");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MemberId that = (MemberId) obj;
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
