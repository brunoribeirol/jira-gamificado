package com.cesar.school.presentation.dto.teamsmembers;

import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.teamsmembers.entity.Member;

import java.util.List;
import java.util.stream.Collectors;

public class MemberResponse {

    public int memberId;
    public String name;
    public String email;
    public String password;
    public String role;
    public int individualScore;
    public boolean isAdmin;                         // <-- novo campo
    public List<Integer> unlockedRewardIds;
    public List<FeedbackResponse> receivedFeedbacks;

    public static MemberResponse fromDomain(Member member) {
        MemberResponse response = new MemberResponse();
        response.memberId = member.getId().getValue();
        response.name = member.getName();
        response.email = member.getEmail();
        response.password = member.getPassword();
        response.role = member.getRole().name();
        response.individualScore = member.getIndividualScore();
        response.isAdmin = member.isAdmin();        // <-- popula
        response.unlockedRewardIds = member.getUnlockedRewardIds()
                .stream()
                .map(RewardId::getValue)
                .collect(Collectors.toList());
        response.receivedFeedbacks = member.getReceivedFeedbacks()
                .stream()
                .map(FeedbackResponse::fromDomain)
                .collect(Collectors.toList());
        return response;
    }
}
