package com.cesar.school.infrastructure.persistence.repository.teamsmembers;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.repository.FeedbackRepository;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.infrastructure.persistence.entity.teamsmembers.MemberEntity;
import com.cesar.school.infrastructure.persistence.mapper.teamsmembers.MemberMapper;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.MemberJpaRepository;
import com.cesar.school.core.teamsmembers.vo.TeamId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository jpaRepository;
    private final FeedbackRepository feedbackRepository;

    public MemberRepositoryImpl(MemberJpaRepository jpaRepository, FeedbackRepository feedbackRepository) {
        this.jpaRepository = jpaRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void save(Member member) {
        MemberEntity entity = MemberMapper.toEntity(member);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Member> findById(MemberId memberId) {
        return jpaRepository.findById(memberId.getValue())
                .map(MemberMapper::toDomain)
                .map(member -> {
                    List<Feedback> feedbacks = feedbackRepository.findByReceivedBy(memberId);
                    feedbacks.forEach(member::receiveFeedback);
                    return member;
                });
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(MemberMapper::toDomain);
    }

    @Override
    public List<Member> findAll() {
        return jpaRepository.findAll().stream()
                .map(MemberMapper::toDomain)
                .peek(member -> {
                    List<Feedback> feedbacks = feedbackRepository.findByReceivedBy(member.getId());
                    feedbacks.forEach(member::receiveFeedback);
                })
                .toList();
    }

    @Override
    public List<Member> findByTeamId(TeamId teamId) {
        return jpaRepository.findByTeamId(teamId.getValue()).stream()
                .map(MemberMapper::toDomain)
                .peek(member -> {
                    List<Feedback> feedbacks = feedbackRepository.findByReceivedBy(member.getId());
                    feedbacks.forEach(member::receiveFeedback);
                })
                .toList();
    }

    @Override
    public void delete(Member member) {
        jpaRepository.deleteById(member.getId().getValue());
    }
}
