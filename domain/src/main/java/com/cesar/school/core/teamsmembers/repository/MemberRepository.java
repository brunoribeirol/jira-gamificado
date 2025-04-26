package com.cesar.school.core.teamsmembers.repository;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;


import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(MemberId memberId);
    void delete(Member member);
    void update(Member member);
}
