package com.cesar.school.infrastructure.persistence.springdata.projectmanagement;

import com.cesar.school.infrastructure.persistence.entity.projectmanagement.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findByProjectId(int projectId);
    @Query(value = "SELECT * FROM tasks t JOIN task_assignees a ON t.id = a.task_id WHERE a.member_id = :memberId", nativeQuery = true)
    List<TaskEntity> findByAssignee(@Param("memberId") int memberId);
}
