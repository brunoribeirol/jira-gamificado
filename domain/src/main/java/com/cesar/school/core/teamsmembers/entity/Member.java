package com.cesar.school.core.teamsmembers.entity;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.teamsmembers.vo.MemberId;
import com.cesar.school.core.teamsmembers.vo.Role;

import java.util.List;

public class Member {
    private MemberId id;
    private String name;
    private String email;
    private String password;
    private Role role; // DEV, QA, PO, LEADER etc.
    private int individualScore;
    private List<Feedback> receivedFeedbacks;
    private List<Reward> unlockedRewards;

    public void receiveFeedback(Feedback feedback) {
        receivedFeedbacks.add(feedback);
    }

    public void addPoints(int points) {
        this.individualScore += points;
    }

    public boolean hasUnlocked(Reward reward) {
        return unlockedRewards.contains(reward);
    }

    public void unlockReward(Reward reward) {
        if (!hasUnlocked(reward)) {
            unlockedRewards.add(reward);
        }
    }
}
