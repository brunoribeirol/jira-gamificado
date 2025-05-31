package com.cesar.school.core.teamsmembers.entity;

import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.vo.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Member {

    private final MemberId id;
    private final String name;
    private final String email;
    private final String password;
    private Role role;
    private int individualScore;
    private final List<Feedback> receivedFeedbacks;
    private final List<RewardId> unlockedRewards;
    private boolean isAdmin;


    /** Construtor legado – continua funcionando (admin = false). */
    public Member(MemberId id, String name, String email, String password, Role role) {
        this(id, name, email, password, role, false);
    }

    /** Novo construtor permitindo definir se é administrador. */
    public Member(MemberId id, String name, String email, String password, Role role, boolean isAdmin) {
        if (name == null || name.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Informações obrigatórias do membro não podem estar vazias");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Objects.requireNonNull(role);
        this.individualScore = 0;
        this.receivedFeedbacks = new ArrayList<>();
        this.unlockedRewards = new ArrayList<>();
        this.isAdmin = isAdmin;
    }

    /* -------------------- BEHAVIOUR -------------------- */

//    public void setRole(Role role) { this.role = Objects.requireNonNull(role); }

    public void changeRole(Role newRole) {
        if (newRole == null) {
            throw new IllegalArgumentException("O novo papel não pode ser nulo");
        }
        // validações futuras (ex: impedir downgrade, exigir score mínimo etc)
        this.role = newRole;
    }

    public void addPoints(int points) {
        if (points < 0) throw new IllegalArgumentException("Pontuação não pode ser negativa");
        individualScore += points;
    }

    public void spendPoints(int points) {
        if (points <= 0)
            throw new IllegalArgumentException("A pontuação a ser gasta deve ser positiva");
        if (individualScore < points)
            throw new IllegalStateException("Pontuação insuficiente para desbloquear a tarefa");
        individualScore -= points;
    }

    public void receiveFeedback(Feedback feedback) {
        if (feedback == null) throw new IllegalArgumentException("Feedback não pode ser nulo");
        receivedFeedbacks.add(feedback);
    }

    public void unlockReward(RewardId rewardId) {
        if (rewardId == null) throw new IllegalArgumentException("ID da recompensa não pode ser nulo");
        unlockedRewards.add(rewardId);
    }

    /* -------------------- ADMIN OPERATIONS -------------------- */

    public boolean isAdmin() { return isAdmin; }

    public void promoteToAdmin() { this.isAdmin = true; }

    public void demoteFromAdmin() { this.isAdmin = false; }

    /* -------------------- GETTERS -------------------- */

    public MemberId getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public Role getRole() { return role; }

    public int getIndividualScore() { return individualScore; }

    public List<Feedback> getReceivedFeedbacks() { return Collections.unmodifiableList(receivedFeedbacks); }

    public List<RewardId> getUnlockedRewardIds() { return List.copyOf(unlockedRewards); }
}
