package com.cesar.school.infrastructure.persistence.springdata.projectmanagement;

import com.cesar.school.infrastructure.persistence.entity.projectmanagement.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findByProjectId(int projectId);
}
