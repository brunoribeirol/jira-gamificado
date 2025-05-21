package com.cesar.school.application.teamsmembers;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.core.teamsmembers.repository.TeamRepository;
import com.cesar.school.core.teamsmembers.service.MemberService;
import com.cesar.school.core.teamsmembers.service.MemberTeamService;
import com.cesar.school.core.teamsmembers.service.TeamService;
import com.cesar.school.core.teamsmembers.vo.Role;
import com.cesar.school.core.teamsmembers.vo.TeamId;
import com.cesar.school.core.teamsmembers.repository.FeedbackRepository;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.Optional;

public class MemberTeamServiceImpl implements MemberTeamService, MemberService, TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final FeedbackRepository feedbackRepository;

    public MemberTeamServiceImpl(TeamRepository teamRepository, MemberRepository memberRepository, FeedbackRepository feedbackRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
        this.feedbackRepository = feedbackRepository;
    }

    // ========== MemberTeamService ==========

    @Override
    public void createTeam(TeamId teamId, String teamName) {
        // Em um cenário real, o ID do líder viria da autenticação
        MemberId leaderId = new MemberId(1);
        List<MemberId> initialMembers = List.of(leaderId);
        Team team = new Team(teamId, teamName, leaderId, initialMembers);
        teamRepository.save(team);
    }

    @Override
    public void addMember(TeamId teamId, MemberId memberId, Role role) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Time não encontrado"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));

        member.setRole(role);
        team.addMember(memberId);

        teamRepository.save(team);
        memberRepository.save(member);
    }

    @Override
    public List<RewardId> getUnlockedRewards(MemberId memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));
        return member.getUnlockedRewardIds();
    }

    @Override
    public int getScore(MemberId memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));
        return member.getIndividualScore();
    }

    @Override
    public List<Feedback> getReceivedFeedbacks(MemberId memberId) {
        return feedbackRepository.findByReceivedBy(memberId); // ✅ busca do banco
    }

    // ========== MemberService ==========

    @Override
    public void addPoints(MemberId memberId, int points) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));
        member.addPoints(points);
        memberRepository.save(member);
    }

    @Transactional
    @Override
    public void addFeedback(MemberId memberId, Feedback feedback) {
        System.out.println(">>> Service: Entrou em addFeedback");
        System.out.println(">>> Feedback ID: " + feedback.getId().getValue());
        System.out.println(">>> FeedbackRepository é nulo? " + (feedbackRepository == null));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));

        member.receiveFeedback(feedback);
        memberRepository.save(member);
        feedbackRepository.save(feedback);
    }

    @Override
    public void unlockReward(MemberId memberId, Reward reward) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));
        member.unlockReward(reward.getId());
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> getById(MemberId memberId) {
        return memberRepository.findById(memberId);
    }

    // ========== TeamService ==========

    @Override
    public void addPoints(TeamId teamId, int points) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Time não encontrado"));
        team.addPoints(points);
        teamRepository.save(team);
    }

    @Override
    public Optional<Team> getById(TeamId teamId) {
        return teamRepository.findById(teamId);
    }
}
