package com.cesar.school.core.teamsmembers.service.impl;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.repository.FeedbackRepository;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.core.teamsmembers.repository.TeamRepository;
import com.cesar.school.core.teamsmembers.service.MemberTeamService;
import com.cesar.school.core.teamsmembers.vo.FeedbackId;
import com.cesar.school.core.teamsmembers.vo.TeamId;

import java.util.List;
import java.util.Optional;

public class MemberTeamServiceDomainImpl implements MemberTeamService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final FeedbackRepository feedbackRepository;

    public MemberTeamServiceDomainImpl(MemberRepository memberRepository, 
                                      TeamRepository teamRepository,
                                      FeedbackRepository feedbackRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Member createMember(MemberId memberId, String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do membro é obrigatório");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email do membro é obrigatório");
        }
        
        Member member = new Member(memberId, name, email);
        memberRepository.save(member);
        return member;
    }

    @Override
    public Team createTeam(TeamId teamId, String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da equipe é obrigatório");
        }
        
        Team team = new Team(teamId, name);
        teamRepository.save(team);
        return team;
    }

    @Override
    public void addMemberToTeam(TeamId teamId, MemberId memberId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Equipe não encontrada"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));
        
        team.addMember(memberId);
        teamRepository.save(team);
    }

    @Override
    public Feedback giveFeedback(FeedbackId feedbackId, MemberId fromMemberId, MemberId toMemberId, String message, int rating) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Mensagem de feedback é obrigatória");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Avaliação deve estar entre 1 e 5");
        }
        
        Member fromMember = memberRepository.findById(fromMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro de origem não encontrado"));
        Member toMember = memberRepository.findById(toMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro de destino não encontrado"));
        
        Feedback feedback = new Feedback(feedbackId, fromMemberId, toMemberId, message, rating);
        feedbackRepository.save(feedback);
        return feedback;
    }

    @Override
    public List<Feedback> listFeedbacksByMember(MemberId memberId) {
        return feedbackRepository.findByToMemberId(memberId);
    }

    @Override
    public Optional<Member> getMemberById(MemberId memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Optional<Team> getTeamById(TeamId teamId) {
        return teamRepository.findById(teamId);
    }

    @Override
    public List<Member> listAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public List<Team> listAllTeams() {
        return teamRepository.findAll();
    }
}
