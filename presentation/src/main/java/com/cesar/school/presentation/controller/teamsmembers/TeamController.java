package com.cesar.school.presentation.controller.teamsmembers;

import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.shared.TeamId;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.vo.Role;
import com.cesar.school.presentation.dto.teamsmembers.AddMemberRequest;
import com.cesar.school.presentation.dto.teamsmembers.CreateTeamRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final MemberTeamServiceImpl memberTeamService;

    public TeamController(MemberTeamServiceImpl memberTeamService) {
        this.memberTeamService = memberTeamService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTeamRequest request) {
        Team team = request.toDomain(); // Usa o leaderId correto
        memberTeamService.createTeam(team);
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

    // GET /api/teams/{teamId}/members
    @GetMapping("/{teamId}/members")
    public ResponseEntity<List<Integer>> getTeamMembers(@PathVariable int teamId) {
        return memberTeamService.getById(new TeamId(teamId))
                .map(team -> {
                    List<Integer> memberIds = new ArrayList<>();
                    Iterator<MemberId> iterator = team.iterator();
                    while (iterator.hasNext()) {
                        MemberId id = iterator.next();
                        memberIds.add(id.getValue());
                    }
                    return ResponseEntity.ok(memberIds);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}