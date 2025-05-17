package com.cesar.school.presentation.dto.teamsmembers;

import java.util.List;

public class MemberResponse {

    public int memberId;
    public List<Integer> unlockedRewardIds;

    public static MemberResponse from(int memberId, List<?> rewards) {
        MemberResponse response = new MemberResponse();
        response.memberId = memberId;
        response.unlockedRewardIds = rewards.stream()
                .map(r -> ((com.cesar.school.core.gamification.vo.RewardId) r).getValue())
                .toList();
        return response;
    }
}
