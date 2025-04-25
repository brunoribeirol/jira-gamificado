package com.cesar.school.core.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LaunchChallengeService {
    private final ChallengeRepository challengeRepository;

    public void execute(Challenge challenge) {
        challengeRepository.save(challenge);
    }
}
