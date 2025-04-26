package com.cesar.school.core.teamsmembers.entity;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.vo.TeamId;

import java.util.Collections;
import java.util.List;

public class Team {
    private final TeamId id;
    private final String name;
    private final MemberId leaderId;
    private final List<MemberId> members;
    private int teamScore;

    public Team(TeamId id, String name, MemberId leaderId, List<MemberId> members) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do time não pode ser vazio");
        }
        if (leaderId == null || members == null || members.isEmpty()) {
            throw new IllegalArgumentException("O time deve ter um líder e membros");
        }

        this.id = id;
        this.name = name;
        this.leaderId = leaderId;
        this.members = List.copyOf(members);  // Garantindo imutabilidade da lista de membros
        this.teamScore = 0;
    }

    public void addMember(MemberId memberId) {
        throw new UnsupportedOperationException("Membros não podem ser alterados após a criação do time");
    }

    public void removeMember(MemberId memberId) {
        throw new UnsupportedOperationException("Membros não podem ser removidos após a criação do time");
    }

    public void addPoints(int points) {
        if (points < 0) throw new IllegalArgumentException("A pontuação não pode ser negativa");
        teamScore += points;
    }

    // Métodos Getters
    public TeamId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MemberId getLeaderId() {
        return leaderId;
    }

    public List<MemberId> getMembers() {
        return Collections.unmodifiableList(members); // Garantindo imutabilidade da lista de membros
    }

    public int getTeamScore() {
        return teamScore;
    }
}
