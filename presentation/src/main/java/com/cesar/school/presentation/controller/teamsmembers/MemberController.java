package com.cesar.school.presentation.controller.teamsmembers;

import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.vo.FeedbackId;
import com.cesar.school.core.shared.MemberId;

import java.util.Date;


import com.cesar.school.core.teamsmembers.service.MemberTeamService;
import com.cesar.school.core.teamsmembers.service.MemberService;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.presentation.dto.teamsmembers.FeedbackResponse;
import com.cesar.school.presentation.dto.teamsmembers.MemberResponse;
import com.cesar.school.presentation.response.ApiResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberTeamService memberTeamService;


    public MemberController(MemberService memberService, MemberTeamService memberTeamService) {
        this.memberService = memberService;
        this.memberTeamService = memberTeamService;
    }

    @PostConstruct
    public void init() {
        System.out.println(">>> Controller: memberService = " + memberService.getClass().getName());
        System.out.println(">>> Controller: memberTeamService = " + memberTeamService.getClass().getName());
    }


    @GetMapping("/{id}/score")
    public ResponseEntity<ApiResponse> getScore(@PathVariable int id) {
        int score = memberTeamService.getScore(new MemberId(id));
        return ResponseEntity.ok(new ApiResponse(true, "Pontuação recuperada com sucesso: " + score));
    }

    @GetMapping("/{id}/feedbacks")
    public ResponseEntity<List<FeedbackResponse>> getFeedbacks(@PathVariable("id") int id) {
        var feedbacks = memberTeamService.getReceivedFeedbacks(new MemberId(id));
        System.out.println("Encontrados: " + feedbacks.size() + " feedbacks");
        var response = feedbacks.stream()
                .map(FeedbackResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test-feedback")
    public ResponseEntity<String> testFeedbackSave() {
        System.out.println(">>> Controller: Entrou no test-feedback");

        var feedback = new Feedback(
                new FeedbackId(0),
                "Testando feedback direto",
                new Date(),
                new MemberId(101),
                new MemberId(104),
                null
        );

        System.out.println(">>> Controller: Chamando memberService.addFeedback");

        memberService.addFeedback(new MemberId(104), feedback);

        System.out.println(">>> Controller: Feedback enviado com sucesso");return ResponseEntity.ok("Test feedback enviado");
    }

    @GetMapping("/{id}/rewards")
    public ResponseEntity<MemberResponse> getUnlockedRewards(@PathVariable int id) {
        var rewardIds = memberTeamService.getUnlockedRewards(new MemberId(id));
        return ResponseEntity.ok(MemberResponse.from(id, rewardIds));
    }
}
