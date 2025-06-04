package com.cesar.school.presentation.controller.teamsmembers;

import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.shared.vo.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.presentation.dto.teamsmembers.GiveFeedbackRequest;
import com.cesar.school.presentation.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final MemberTeamServiceImpl memberTeamService;

    public FeedbackController(MemberTeamServiceImpl memberTeamService) {
        this.memberTeamService = memberTeamService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> giveFeedback(@RequestBody GiveFeedbackRequest request) {
        memberTeamService.addFeedback(
                new MemberId(request.receivedBy),
                request.toDomain()
        );
        return ResponseEntity.ok(new ApiResponse(true, "Feedback enviado com sucesso."));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<Feedback>> getFeedbacks(@PathVariable int memberId) {
        List<Feedback> feedbacks = memberTeamService.getReceivedFeedbacks(new MemberId(memberId));
        return ResponseEntity.ok(feedbacks);
    }
}
