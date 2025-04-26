package com.cesar.school.core.projectmanagement.service;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.vo.ChallengeId;
import com.cesar.school.core.projectmanagement.vo.ProjectId;

import java.util.List;
import java.util.Optional;

public interface ChallengeService {
    void addToProject(ProjectId projectId, Challenge challenge);
    Optional<Challenge> getById(ChallengeId id);
    void update(Challenge challenge);
    void delete(ChallengeId id);
    List<Challenge> listByProject(ProjectId projectId);
}
