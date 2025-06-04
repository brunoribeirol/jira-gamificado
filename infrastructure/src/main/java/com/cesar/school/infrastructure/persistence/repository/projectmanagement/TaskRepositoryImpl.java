package com.cesar.school.infrastructure.persistence.repository.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.shared.vo.ProjectId;
import com.cesar.school.core.shared.vo.TaskId;
import com.cesar.school.core.shared.vo.MemberId;
import com.cesar.school.infrastructure.persistence.entity.projectmanagement.TaskEntity;
import com.cesar.school.infrastructure.persistence.mapper.projectmanagement.TaskMapper;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.TaskJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepositoryImpl implements TaskRepository {

    private final TaskJpaRepository jpaRepository;

    public TaskRepositoryImpl(TaskJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return jpaRepository.findById(id.getValue())
                .map(TaskMapper::toDomain);
    }

    @Override
    public void save(Task task) {
        TaskEntity entity = TaskMapper.toEntity(task);
        jpaRepository.save(entity);
    }

    @Override
    public void save(Task task, ProjectId projectId) {
        TaskEntity entity = TaskMapper.toEntity(task, projectId);
        jpaRepository.save(entity);
    }

    @Override
    public void deleteById(TaskId id) {
        jpaRepository.deleteById(id.getValue());
    }

    @Override
    public List<Task> findByProjectId(ProjectId projectId) {
        return jpaRepository.findByProjectId(projectId.getValue())
                .stream()
                .map(TaskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findByAssignee(MemberId memberId) {
        return jpaRepository.findByAssignee(memberId.getValue())
                .stream()
                .map(TaskMapper::toDomain)
                .collect(Collectors.toList());
    }
}