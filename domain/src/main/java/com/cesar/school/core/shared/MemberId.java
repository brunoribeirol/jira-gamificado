package com.cesar.school.core.shared;

import java.util.Objects;

public class MemberId {
    private final int value;  // Aqui o tipo já está como int

    public MemberId(int value) {
        if (value <= 0) {  // Remove a verificação de null, pois valor nunca será null
            throw new IllegalArgumentException("MemberId deve ser um número positivo.");
        }
        this.value = value;
    }

    public int getValue() {  // O método getValue agora retorna um int
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberId)) return false;
        MemberId memberId = (MemberId) o;
        return value == memberId.value;  // Comparando valores int diretamente
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);  // Usando o valor int para o cálculo do hash
    }
}
