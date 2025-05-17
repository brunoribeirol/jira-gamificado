package com.cesar.school.infrastructure.persistence.springdata.projectmanagement;

import com.cesar.school.infrastructure.persistence.entity.projectmanagement.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeJpaRepository extends JpaRepository<ChallengeEntity, Integer> {
    List<ChallengeEntity> findByProjectId(int projectId);
}
