package com.cesar.school.core.gamification;

import com.cesar.school.core.teams.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UnlockRewardService {
    private final RewardRepository rewardRepository;

    public void execute(Member member, Reward reward) {
        reward.setUnlocked(true);
        reward.setMember(member);
        rewardRepository.save(reward);
    }
}
