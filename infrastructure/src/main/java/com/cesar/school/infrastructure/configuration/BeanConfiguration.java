package com.cesar.school.infrastructure.configuration;

import com.cesar.school.application.gamification.RewardServiceImpl;
import com.cesar.school.application.projectmanagement.ChallengeServiceImpl;
import com.cesar.school.application.projectmanagement.ProjectServiceImpl;
import com.cesar.school.application.projectmanagement.TaskServiceImpl;
import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;

import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.projectmanagement.repository.ChallengeRepository;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.strategy.BonusForPairProgrammingStrategy;
import com.cesar.school.core.projectmanagement.strategy.DefaultScoreStrategy;
import com.cesar.school.core.projectmanagement.strategy.TaskScoreStrategy;
import com.cesar.school.core.teamsmembers.repository.FeedbackRepository;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.core.teamsmembers.repository.TeamRepository;

import com.cesar.school.infrastructure.persistence.repository.gamification.RewardRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.projectmanagement.ChallengeRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.projectmanagement.ProjectRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.projectmanagement.TaskRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.teamsmembers.FeedbackRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.teamsmembers.MemberRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.teamsmembers.TeamRepositoryImpl;

import com.cesar.school.infrastructure.persistence.springdata.gamification.RewardJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.ChallengeJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.ProjectJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.projectmanagement.TaskJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.FeedbackJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.MemberJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.TeamJpaRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.cesar.school.infrastructure.persistence.springdata")
public class BeanConfiguration {

    // Repositories
    @Bean
    public RewardRepository rewardRepository(RewardJpaRepository jpa) {
        return new RewardRepositoryImpl(jpa);
    }

    @Bean
    public ProjectRepository projectRepository(ProjectJpaRepository jpa, TaskJpaRepository taskJpa) {
        return new ProjectRepositoryImpl(jpa, taskJpa);
    }

    @Bean
    public TaskRepository taskRepository(TaskJpaRepository jpa) {
        return new TaskRepositoryImpl(jpa);
    }

    @Bean
    public ChallengeRepository challengeRepository(ChallengeJpaRepository jpa) {
        return new ChallengeRepositoryImpl(jpa);
    }

    @Bean
    public FeedbackRepository feedbackRepository(FeedbackJpaRepository jpa) {
        return new FeedbackRepositoryImpl(jpa);
    }

    @Bean
    public MemberRepository memberRepository(MemberJpaRepository jpa, FeedbackRepository feedbackRepository) {
        return new MemberRepositoryImpl(jpa, feedbackRepository);
    }

    @Bean
    public TeamRepository teamRepository(TeamJpaRepository jpa) {
        return new TeamRepositoryImpl(jpa);
    }

    // Services
    @Bean
    public RewardServiceImpl rewardService(RewardRepository rewardRepository) {
        return new RewardServiceImpl(rewardRepository);
    }

    @Bean
    public ProjectServiceImpl projectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        return new ProjectServiceImpl(projectRepository, taskRepository);
    }

    @Bean
    public TaskServiceImpl taskService(
            TaskRepository taskRepository,
            ProjectRepository projectRepository,
            MemberRepository memberRepository,
            TaskScoreStrategy taskScoreStrategy
    ) {
        return new TaskServiceImpl(taskRepository, projectRepository, memberRepository, taskScoreStrategy);
    }

    @Bean
    public ChallengeServiceImpl challengeService(ChallengeRepository challengeRepository, ProjectRepository projectRepository) {
        return new ChallengeServiceImpl(challengeRepository, projectRepository);
    }

    @Bean
    public MemberTeamServiceImpl memberTeamService(
            TeamRepository teamRepository,
            MemberRepository memberRepository,
            RewardRepository rewardRepository,
            FeedbackRepository feedbackRepository
    ) {
        return new MemberTeamServiceImpl(
                teamRepository,
                memberRepository,
                rewardRepository,
                feedbackRepository
        );
    }

    // Strategy Bean (Pattern Strategy)
    @Bean
    public TaskScoreStrategy taskScoreStrategy() {
        return new BonusForPairProgrammingStrategy(10);
    }
}
