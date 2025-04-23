package com.cesar.school.core.projectmanagement;

import java.util.Optional;

public interface ChallengeRepository {
    Optional<Challenge> findById(Long id);
    void save(Challenge challenge);
}