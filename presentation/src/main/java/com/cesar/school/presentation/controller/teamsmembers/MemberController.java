package com.cesar.school.presentation.controller.teamsmembers;

import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.presentation.dto.teamsmembers.*;
import com.cesar.school.presentation.response.ApiResponse;
import jakarta.validation.Valid;
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

    // POST /api/members
    @PostMapping
    public ResponseEntity<ApiResponse> createMember(@Valid @RequestBody CreateMemberRequest request) {
        memberService.createMember(request.toDomain());
        return ResponseEntity.ok(new ApiResponse(true, "Membro criado com sucesso."));
    }

    // GET /api/members
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(
                memberService.getAll().stream()
                        .map(MemberResponse::fromDomain)
                        .collect(Collectors.toList())
        );
    }

    // GET /api/members/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable int id) {
        return memberService.getById(new MemberId(id))
                .map(MemberResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH /api/members/{id}/points
    @PatchMapping("/{id}/points")
    public ResponseEntity<ApiResponse> addPoints(@PathVariable int id, @RequestBody AddPointsRequest request) {
        memberService.addPoints(new MemberId(id), request.points);
        return ResponseEntity.ok(new ApiResponse(true, "Pontos adicionados com sucesso"));
    }

    // PATCH /api/members/{id}/rewards
    @PatchMapping("/{id}/rewards")
    public ResponseEntity<ApiResponse> unlockReward(
            @PathVariable int id,
            @RequestBody RewardIdRequest request
    ) {
        memberService.unlockReward(new MemberId(id), new RewardId(request.rewardId));
        return ResponseEntity.ok(new ApiResponse(true, "Recompensa desbloqueada com sucesso"));
    }


    // POST /api/members/{id}/feedbacks
    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<ApiResponse> addFeedback(
            @PathVariable int id,
            @Valid @RequestBody FeedbackRequest request
    ) {
        memberService.addFeedback(new MemberId(id), request.toDomain());
        return ResponseEntity.ok(new ApiResponse(true, "Feedback adicionado com sucesso"));
    }
}
