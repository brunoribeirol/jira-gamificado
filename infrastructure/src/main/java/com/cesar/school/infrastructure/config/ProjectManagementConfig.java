package com.cesar.school.infrastructure.config;

import com.cesar.school.application.projectmanagement.ChallengeServiceImpl;
import com.cesar.school.application.projectmanagement.ProjectServiceImpl;
import com.cesar.school.application.projectmanagement.TaskServiceImpl;
import com.cesar.school.core.projectmanagement.repository.ChallengeRepository;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.service.ChallengeService;
import com.cesar.school.core.projectmanagement.service.ProjectService;
import com.cesar.school.core.projectmanagement.service.TaskService;
import com.cesar.school.core.projectmanagement.service.impl.ChallengeServiceDomainImpl;
import com.cesar.school.core.projectmanagement.service.impl.ProjectServiceDomainImpl;
import com.cesar.school.core.projectmanagement.service.impl.TaskServiceDomainImpl;
import com.cesar.school.infrastructure.persistence.repository.projectmanagement.ChallengeRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.projectmanagement.ProjectRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.projectmanagement.TaskRepositoryImpl;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.ChallengeJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.ProjectJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.TaskJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectManagementConfig {

    // Repositórios
    @Bean
    public ChallengeRepository challengeRepository(ChallengeJpaRepository challengeJpaRepository) {
        return new ChallengeRepositoryImpl(challengeJpaRepository);
    }

    @Bean
    public ProjectRepository projectRepository(ProjectJpaRepository projectJpaRepository) {
        return new ProjectRepositoryImpl(projectJpaRepository);
    }

    @Bean
    public TaskRepository taskRepository(TaskJpaRepository taskJpaRepository) {
        return new TaskRepositoryImpl(taskJpaRepository);
    }

    // Serviços de Domínio
    @Bean
    public ChallengeService challengeServiceDomain(ChallengeRepository challengeRepository, ProjectRepository projectRepository) {
        return new ChallengeServiceDomainImpl(challengeRepository, projectRepository);
    }

    @Bean
    public ProjectService projectServiceDomain(ProjectRepository projectRepository) {
        return new ProjectServiceDomainImpl(projectRepository);
    }

    @Bean
    public TaskService taskServiceDomain(TaskRepository taskRepository, ProjectRepository projectRepository) {
        return new TaskServiceDomainImpl(taskRepository, projectRepository);
    }

    // Adaptadores de Aplicação
    @Bean
    public ChallengeServiceImpl challengeServiceImpl(ChallengeService challengeService) {
        return new ChallengeServiceImpl(challengeService);
    }

    @Bean
    public ProjectServiceImpl projectServiceImpl(ProjectService projectService) {
        return new ProjectServiceImpl(projectService);
    }

    @Bean
    public TaskServiceImpl taskServiceImpl(TaskService taskService) {
        return new TaskServiceImpl(taskService);
    }
}
