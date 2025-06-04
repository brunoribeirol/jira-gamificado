package com.cesar.school.presentation.controller.teamsmembers;

import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.shared.vo.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.shared.Role;
import com.cesar.school.core.shared.vo.TeamId;
import com.cesar.school.presentation.dto.teamsmembers.AddMemberRequest;
import com.cesar.school.presentation.dto.teamsmembers.CreateTeamRequest;
import com.cesar.school.presentation.dto.teamsmembers.MemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final MemberTeamServiceImpl memberTeamService;

    public TeamController(MemberTeamServiceImpl memberTeamService) {
        this.memberTeamService = memberTeamService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTeamRequest request) {
        memberTeamService.createTeam(
                new TeamId(request.id),
                request.name,
                new MemberId(request.leaderId)
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = memberTeamService.findAll();
        return ResponseEntity.ok(teams);
    }

    @PostMapping("/{teamId}/members")
    public ResponseEntity<Void> addMember(@PathVariable int teamId, @RequestBody AddMemberRequest request) {
        memberTeamService.addMember(new TeamId(teamId), new MemberId(request.memberId), Role.valueOf(request.role));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeam(@PathVariable int teamId) {
        return memberTeamService.getById(new TeamId(teamId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{teamId}/members")
    public ResponseEntity<List<MemberResponse>> getTeamMembers(@PathVariable int teamId) {
        List<Member> members = memberTeamService.findMembersByTeamId(new TeamId(teamId));
        List<MemberResponse> responses = members.stream()
                .map(MemberResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}