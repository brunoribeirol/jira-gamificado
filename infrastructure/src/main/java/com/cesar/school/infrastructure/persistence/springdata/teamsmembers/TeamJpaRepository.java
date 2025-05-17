package com.cesar.school.infrastructure.persistence.springdata.teamsmembers;

import com.cesar.school.infrastructure.persistence.entity.teamsmembers.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamJpaRepository extends JpaRepository<TeamEntity, Integer> {
}