package com.cesar.school.core.teamsmembers.repository;

import com.cesar.school.core.shared.vo.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.shared.vo.FeedbackId;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository {
    void save(Feedback feedback);
    Optional<Feedback> findById(FeedbackId id);
    List<Feedback> findByReceivedBy(MemberId memberId);
}
