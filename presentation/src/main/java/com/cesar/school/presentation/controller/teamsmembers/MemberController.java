package com.cesar.school.presentation.controller.teamsmembers;

import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.presentation.dto.teamsmembers.FeedbackResponse;
import com.cesar.school.presentation.dto.teamsmembers.MemberResponse;
import com.cesar.school.presentation.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberTeamServiceImpl memberService;

    public MemberController(MemberTeamServiceImpl memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}/score")
    public ResponseEntity<ApiResponse> getScore(@PathVariable int id) {
        int score = memberService.getScore(new MemberId(id));
        return ResponseEntity.ok(new ApiResponse(true, "Pontuação recuperada com sucesso: " + score));
    }

    @GetMapping("/{id}/feedbacks")
    public ResponseEntity<List<FeedbackResponse>> getFeedbacks(@PathVariable("id") int id) {
        var feedbacks = memberService.getReceivedFeedbacks(new MemberId(id));
        var response = feedbacks.stream()
                .map(FeedbackResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/rewards")
    public ResponseEntity<MemberResponse> getUnlockedRewards(@PathVariable int id) {
        var rewardIds = memberService.getUnlockedRewards(new MemberId(id));
        return ResponseEntity.ok(MemberResponse.from(id, rewardIds));
    }
}
