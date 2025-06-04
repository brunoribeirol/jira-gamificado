package com.cesar.school.core.projectmanagement.repository;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.shared.vo.ChallengeId;
import java.util.List;
import com.cesar.school.core.shared.vo.ProjectId;
import java.util.Optional;



public interface ChallengeRepository {
    Optional<Challenge> findById(ChallengeId id);
    void save(Challenge challenge);
    void deleteById(ChallengeId id);
    List<Challenge> findAllByProjectId(ProjectId projectId);
}
