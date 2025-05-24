package com.cesar.school.infrastructure.persistence.repository.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.infrastructure.persistence.entity.projectmanagement.ProjectEntity;
import com.cesar.school.infrastructure.persistence.mapper.projectmanagement.ProjectMapper;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.ProjectJpaRepository;

import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectJpaRepository jpaRepository;

    public ProjectRepositoryImpl(ProjectJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Project project) {
        ProjectEntity entity = ProjectMapper.toEntity(project);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Project> findById(ProjectId id) {
        return jpaRepository.findById(id.getValue())
                .map(ProjectMapper::toDomain);
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
