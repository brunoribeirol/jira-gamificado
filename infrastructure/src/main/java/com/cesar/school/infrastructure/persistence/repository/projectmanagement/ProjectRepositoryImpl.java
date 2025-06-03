package com.cesar.school.infrastructure.persistence.repository.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.infrastructure.persistence.entity.projectmanagement.ProjectEntity;
import com.cesar.school.infrastructure.persistence.mapper.projectmanagement.ProjectMapper;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.ProjectJpaRepository;
import com.cesar.school.infrastructure.persistence.entity.projectmanagement.TaskEntity;
import com.cesar.school.infrastructure.persistence.mapper.projectmanagement.TaskMapper;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.TaskJpaRepository;

import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectJpaRepository jpaRepository;
    private final TaskJpaRepository taskJpaRepository;

    public ProjectRepositoryImpl(ProjectJpaRepository jpaRepository, TaskJpaRepository taskJpaRepository) {
        this.jpaRepository = jpaRepository;
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public Project save(Project project) {
        ProjectEntity entity = ProjectMapper.toEntity(project);
        ProjectEntity saved = jpaRepository.save(entity);
        return ProjectMapper.toDomain(saved);
    }

    @Override
    public Optional<Project> findById(ProjectId id) {
        return jpaRepository.findById(id.getValue())
                .map(projectEntity -> {
                    Project project = ProjectMapper.toDomain(projectEntity);

                    List<TaskEntity> taskEntities = taskJpaRepository.findByProjectId(id.getValue());
                    for (TaskEntity taskEntity : taskEntities) {
                        project.addTask(TaskMapper.toDomain(taskEntity));
                    }

                    return project;
                });
    }

    @Override
    public List<Project> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(ProjectMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(ProjectId id) {
        jpaRepository.deleteById(id.getValue());
    }
}
