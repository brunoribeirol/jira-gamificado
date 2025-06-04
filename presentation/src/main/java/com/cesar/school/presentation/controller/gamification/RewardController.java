package com.cesar.school.presentation.controller.gamification;

import com.cesar.school.application.gamification.RewardServiceImpl;
import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.shared.vo.MemberId;
import com.cesar.school.presentation.dto.gamification.CreateRewardRequest;
import com.cesar.school.presentation.dto.gamification.RewardResponse;
import com.cesar.school.presentation.dto.gamification.UpdateRewardPointsRequest;
import com.cesar.school.presentation.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardServiceImpl rewardService;
    private final MemberTeamServiceImpl memberTeamService;

    public RewardController(RewardServiceImpl rewardService, MemberTeamServiceImpl memberTeamService) {
        this.rewardService = rewardService;
        this.memberTeamService = memberTeamService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createReward(@Valid @RequestBody CreateRewardRequest request) {
        Reward reward = request.toDomain();
        rewardService.createReward(reward);
        return ResponseEntity.ok(new ApiResponse(true, "Recompensa criada com sucesso."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RewardResponse> getRewardById(@PathVariable("id") int id) {
        return rewardService.getRewardById(new RewardId(id))
                .map(RewardResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RewardResponse>> getAllRewards() {
        List<RewardResponse> responses = rewardService.getAllRewards()
                .stream()
                .map(RewardResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/available/{points}")
    public ResponseEntity<List<RewardResponse>> getAvailableRewards(@PathVariable("points") int points) {
        List<RewardResponse> responses = rewardService.getRewardsAvailableForPoints(points)
                .stream()
                .map(RewardResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}/points")
    public ResponseEntity<ApiResponse> updateRewardPoints(
            @PathVariable("id") int id,
            @Valid @RequestBody UpdateRewardPointsRequest request) {

        Optional<Reward> optional = rewardService.getRewardById(new RewardId(id));
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reward existing = optional.get();

        Reward updated = new Reward(
                existing.getId(),
                existing.getDescription(),
                request.requiredPoints,
                existing.getType(),
                existing.getCreatedBy()
        );

        rewardService.createReward(updated);
        return ResponseEntity.ok(new ApiResponse(true, "Pontuação da recompensa atualizada com sucesso."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteReward(@PathVariable("id") int id) {
        rewardService.deleteReward(new RewardId(id));
        return ResponseEntity.ok(new ApiResponse(true, "Recompensa removida com sucesso."));
    }

    // GET /api/rewards/unlocked/{memberId}
    @GetMapping("/unlocked/{memberId}")
    public ResponseEntity<List<RewardResponse>> getUnlockedRewardsByMember(@PathVariable int memberId) {
        List<RewardResponse> rewards = memberTeamService.getUnlockedRewards(new MemberId(memberId)).stream()
                .map(rewardService::getRewardById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(RewardResponse::fromDomain)
                .collect(Collectors.toList());

        return ResponseEntity.ok(rewards);
    }

}
