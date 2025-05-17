package com.cesar.school.infrastructure.persistence.springdata.teamsmembers;

import com.cesar.school.infrastructure.persistence.entity.teamsmembers.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackJpaRepository extends JpaRepository<FeedbackEntity, Integer> {
    List<FeedbackEntity> findByReceivedBy(int memberId);
}
