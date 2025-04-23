package com.cesar.school.core.projectmanagement;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LaunchChallengeService {
    private final ChallengeRepository challengeRepository;

    public void execute(Challenge challenge) {
        challengeRepository.save(challenge);
    }
}
