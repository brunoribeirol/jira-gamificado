package com.cesar.school.core.teams;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(Long id);
    void save(Member member);
}