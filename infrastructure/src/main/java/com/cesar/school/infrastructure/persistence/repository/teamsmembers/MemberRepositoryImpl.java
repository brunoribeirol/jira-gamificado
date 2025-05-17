package com.cesar.school.infrastructure.persistence.repository.teamsmembers;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.infrastructure.persistence.entity.teamsmembers.MemberEntity;
import com.cesar.school.infrastructure.persistence.mapper.teamsmembers.MemberMapper;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.MemberJpaRepository;

import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository jpaRepository;

    public MemberRepositoryImpl(MemberJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Member member) {
        MemberEntity entity = MemberMapper.toEntity(member);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Member> findById(MemberId memberId) {
        return jpaRepository.findById(memberId.getValue())
                .map(MemberMapper::toDomain);
    }

    @Override
    public void delete(Member member) {
        jpaRepository.deleteById(member.getId().getValue());
    }
}