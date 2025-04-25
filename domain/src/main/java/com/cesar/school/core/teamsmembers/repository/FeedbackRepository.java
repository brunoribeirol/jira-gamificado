package com.cesar.school.core.teamsmembers.repository;

import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.vo.MemberId;

import java.util.List;

public interface FeedbackRepository {
    void save(Feedback feedback);
    List<Feedback> findByMemberId(MemberId id);
}
