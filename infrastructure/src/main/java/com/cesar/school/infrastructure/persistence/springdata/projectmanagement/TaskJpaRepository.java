package com.cesar.school.infrastructure.persistence.springdata.projectmanagement;

import com.cesar.school.infrastructure.persistence.entity.projectmanagement.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Integer> {
}