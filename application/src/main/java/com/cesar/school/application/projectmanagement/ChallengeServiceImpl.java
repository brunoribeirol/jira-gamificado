package com.cesar.school.application.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.repository.ChallengeRepository;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.service.ChallengeService;
import com.cesar.school.core.projectmanagement.vo.ChallengeId;
import com.cesar.school.core.projectmanagement.vo.ProjectId;

import java.util.List;
import java.util.Optional;

public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ProjectRepository projectRepository;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository, ProjectRepository projectRepository) {
        this.challengeRepository = challengeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void addToProject(ProjectId projectId, Challenge challenge) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        project.addChallenge(challenge);
        challengeRepository.save(challenge);
        projectRepository.save(project);
    }

    @Override
    public Optional<Challenge> getById(ChallengeId id) {
        return challengeRepository.findById(id);
    }

    @Override
    public void update(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    @Override
    public void delete(ChallengeId id) {
        challengeRepository.deleteById(id);
    }

    @Override
    public List<Challenge> listByProject(ProjectId projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        return challengeRepository.findAllByProjectId(projectId);
    }
}
