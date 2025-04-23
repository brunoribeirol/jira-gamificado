package com.cesar.school.core.teams;

import java.util.Optional;

public interface TeamRepository {
    Optional<Team> findById(Long id);
    void save(Team team);
}