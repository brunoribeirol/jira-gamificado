package com.cesar.school.core.teamsmembers.service;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;


public interface MemberService {
    void saveMember(Member member);
    Member getMemberById(MemberId memberId);
    void updateMember(Member member);
    void deleteMember(Member member);
}
