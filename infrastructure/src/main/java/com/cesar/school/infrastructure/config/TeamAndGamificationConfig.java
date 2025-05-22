package com.cesar.school.infrastructure.config;

import com.cesar.school.application.gamification.RewardServiceImpl;
import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.gamification.service.RewardService;
import com.cesar.school.core.gamification.service.impl.RewardServiceDomainImpl;
import com.cesar.school.core.teamsmembers.repository.FeedbackRepository;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.core.teamsmembers.repository.TeamRepository;
import com.cesar.school.core.teamsmembers.service.MemberTeamService;
import com.cesar.school.core.teamsmembers.service.impl.MemberTeamServiceDomainImpl;
import com.cesar.school.infrastructure.persistence.repository.gamification.RewardRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.teamsmembers.FeedbackRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.teamsmembers.MemberRepositoryImpl;
import com.cesar.school.infrastructure.persistence.repository.teamsmembers.TeamRepositoryImpl;
import com.cesar.school.infrastructure.persistence.springdata.gamification.RewardJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.FeedbackJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.MemberJpaRepository;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.TeamJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamAndGamificationConfig {

    // Repositórios
    @Bean
    public MemberRepository memberRepository(MemberJpaRepository memberJpaRepository) {
        return new MemberRepositoryImpl(memberJpaRepository);
    }

    @Bean
    public TeamRepository teamRepository(TeamJpaRepository teamJpaRepository) {
        return new TeamRepositoryImpl(teamJpaRepository);
    }

    @Bean
    public FeedbackRepository feedbackRepository(FeedbackJpaRepository feedbackJpaRepository) {
        return new FeedbackRepositoryImpl(feedbackJpaRepository);
    }

    @Bean
    public RewardRepository rewardRepository(RewardJpaRepository rewardJpaRepository) {
        return new RewardRepositoryImpl(rewardJpaRepository);
    }

    // Serviços de Domínio
    @Bean
    public MemberTeamService memberTeamServiceDomain(MemberRepository memberRepository, 
                                                    TeamRepository teamRepository,
                                                    FeedbackRepository feedbackRepository) {
        return new MemberTeamServiceDomainImpl(memberRepository, teamRepository, feedbackRepository);
    }

    @Bean
    public RewardService rewardServiceDomain(RewardRepository rewardRepository) {
        return new RewardServiceDomainImpl(rewardRepository);
    }

    // Adaptadores de Aplicação
    @Bean
    public MemberTeamServiceImpl memberTeamServiceImpl(MemberTeamService memberTeamService) {
        return new MemberTeamServiceImpl(memberTeamService);
    }

    @Bean
    public RewardServiceImpl rewardServiceImpl(RewardService rewardService) {
        return new RewardServiceImpl(rewardService);
    }
}
