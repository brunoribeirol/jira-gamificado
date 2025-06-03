package com.cesar.school.core.teamsmembers.repository;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.vo.TeamId;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(MemberId memberId);
    Optional<Member> findByEmail(String email);
    List<Member> findAll();
    List<Member> findByTeamId(TeamId teamId);
    void delete(Member member);
}
