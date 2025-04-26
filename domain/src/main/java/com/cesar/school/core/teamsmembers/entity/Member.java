package com.cesar.school.core.teamsmembers.entity;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.vo.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    private final MemberId id;
    private final String name;
    private final String email;
    private final String password;
    private final Role role;
    private int individualScore;
    private final List<Feedback> receivedFeedbacks;
    private final List<Reward> unlockedRewards;

    public Member(MemberId id, String name, String email, String password, Role role) {
        if (name == null || name.isBlank() || email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Informações obrigatórias do membro não podem estar vazias");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.individualScore = 0;
        this.receivedFeedbacks = new ArrayList<>();
        this.unlockedRewards = new ArrayList<>();
    }

    // Modificar a pontuação de forma controlada
    public void addPoints(int points) {
        if (points < 0) throw new IllegalArgumentException("Pontuação não pode ser negativa");
        individualScore += points;
    }

    public void addFeedback(Feedback feedback) {
        if (feedback == null) throw new IllegalArgumentException("Feedback não pode ser nulo");
        receivedFeedbacks.add(feedback);
    }

    public void unlockReward(Reward reward) {
        if (reward == null) throw new IllegalArgumentException("Recompensa não pode ser nula");
        unlockedRewards.add(reward);
    }

    // Métodos Getter que garantem imutabilidade nas listas
    public MemberId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public int getIndividualScore() {
        return individualScore;
    }

    public List<Feedback> getReceivedFeedbacks() {
        return Collections.unmodifiableList(receivedFeedbacks); // Garantir imutabilidade
    }

    public List<Reward> getUnlockedRewards() {
        return Collections.unmodifiableList(unlockedRewards); // Garantir imutabilidade
    }
}
