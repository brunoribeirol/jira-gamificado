package com.cesar.school.core.teamsmembers.entity;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.shared.TeamId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Iterator;

public class Team implements Iterable<MemberId> {
    private final TeamId id;
    private final String name;
    private final MemberId leaderId;
    private final List<MemberId> members;
    private int teamScore;

    public Team(TeamId id, String name, MemberId leaderId, List<MemberId> initialMembers) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do time não pode ser vazio");
        }

        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.leaderId = leaderId;
        this.members = new ArrayList<>(initialMembers);
        this.teamScore = 0;
    }

    public void addMember(MemberId memberId) {
        if (!members.contains(memberId)) {
            members.add(memberId);
        }
    }

    public void removeMember(MemberId memberId) {
        members.remove(memberId);
    }

    public void addPoints(int points) {
        if (points < 0) throw new IllegalArgumentException("A pontuação não pode ser negativa");
        teamScore += points;
    }

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
        return Collections.unmodifiableList(members);
    }
    @Override
    public Iterator<MemberId> iterator() {
        return members.iterator();
    }

    public int getTeamScore() {
        return teamScore;
    }
}