package com.cesar.school.application.teamsmembers;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.shared.TeamId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.repository.FeedbackRepository;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.core.teamsmembers.repository.TeamRepository;
import com.cesar.school.core.teamsmembers.service.MemberService;
import com.cesar.school.core.teamsmembers.service.MemberTeamService;
import com.cesar.school.core.teamsmembers.service.TeamService;
import com.cesar.school.core.teamsmembers.vo.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberTeamServiceImpl implements MemberTeamService, MemberService, TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final RewardRepository rewardRepository;
    private final FeedbackRepository feedbackRepository;

    public MemberTeamServiceImpl(
            TeamRepository teamRepository,
            MemberRepository memberRepository,
            RewardRepository rewardRepository,
            FeedbackRepository feedbackRepository
    ) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
        this.rewardRepository = rewardRepository;
        this.feedbackRepository = feedbackRepository;
    }


    @Override
    public void createMember(Member member) {
        memberRepository.findByEmail(member.getEmail()).ifPresent(existing -> {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        });
        memberRepository.save(member);
    }

    @Override
    public void createTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void addMember(TeamId teamId, MemberId memberId, Role role) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Time não encontrado"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));

        member.changeRole(role);

        if (!team.getMembers().contains(memberId)) {
            team.addMember(memberId);
        }

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
        return feedbackRepository.findByReceivedBy(memberId);
    }


    @Override
    public void addPoints(MemberId memberId, int points) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));
        member.addPoints(points);
        memberRepository.save(member);
    }

    @Override
    public void addFeedback(MemberId memberId, Feedback feedback) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));
        member.receiveFeedback(feedback);
        feedbackRepository.save(feedback);
    }

    @Override
    @jakarta.transaction.Transactional          // mantém o módulo application "clean"
    public void unlockReward(MemberId memberId, RewardId rewardId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));

        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("Recompensa não encontrada"));

        // debita pontos — lança exceção automática se saldo insuficiente
        member.spendPoints(reward.getRequiredPoints());

        // adiciona a recompensa desbloqueada
        member.unlockReward(reward.getId());

        // persiste o novo saldo e o unlock em uma única transação
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> getById(MemberId memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public List<Member> getAll() {
        return memberRepository.findAll();
    }
    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

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

    @Override
    public List<Member> findMembersByTeamId(TeamId teamId) {
        return memberRepository.findByTeamId(teamId);
    }

    @Override
    public boolean isTeamLeader(MemberId memberId, TeamId teamId) {
        return teamRepository.findById(teamId)
                .map(team -> team.getLeaderId().equals(memberId))
                .orElse(false);
    }
}
