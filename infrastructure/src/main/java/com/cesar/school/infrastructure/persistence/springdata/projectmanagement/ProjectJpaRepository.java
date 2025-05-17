package com.cesar.school.infrastructure.persistence.springdata.projectmanagement;

import com.cesar.school.infrastructure.persistence.entity.projectmanagement.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Integer> {
}
