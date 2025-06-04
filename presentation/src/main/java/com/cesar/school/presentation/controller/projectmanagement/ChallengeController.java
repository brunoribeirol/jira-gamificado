package com.cesar.school.presentation.controller.projectmanagement;

import com.cesar.school.application.projectmanagement.ChallengeServiceImpl;
import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.shared.vo.ChallengeId;
import com.cesar.school.core.shared.vo.ProjectId;
import com.cesar.school.presentation.dto.projectmanagement.challenge.ChallengeResponse;
import com.cesar.school.presentation.dto.projectmanagement.challenge.CreateChallengeRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeServiceImpl challengeService;

    public ChallengeController(ChallengeServiceImpl challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping
    public ResponseEntity<Void> createChallenge(@Valid @RequestBody CreateChallengeRequest request) {
        Challenge challenge = request.toDomain();
        challengeService.addToProject(new ProjectId(request.projectId), challenge);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeResponse> getChallengeById(@PathVariable int id) {
        return challengeService.getById(new ChallengeId(id))
                .map(ChallengeResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ChallengeResponse>> listByProject(@PathVariable int projectId) {
        List<ChallengeResponse> responses = challengeService.listByProject(new ProjectId(projectId))
                .stream()
                .map(ChallengeResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable int id) {
        challengeService.delete(new ChallengeId(id));
        return ResponseEntity.noContent().build();
    }
}
