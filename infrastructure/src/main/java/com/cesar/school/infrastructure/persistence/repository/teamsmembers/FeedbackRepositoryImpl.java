package com.cesar.school.infrastructure.persistence.repository.teamsmembers;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.repository.FeedbackRepository;
import com.cesar.school.core.teamsmembers.vo.FeedbackId;
import com.cesar.school.infrastructure.persistence.entity.teamsmembers.FeedbackEntity;
import com.cesar.school.infrastructure.persistence.mapper.teamsmembers.FeedbackMapper;
import com.cesar.school.infrastructure.persistence.springdata.teamsmembers.FeedbackJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FeedbackRepositoryImpl implements FeedbackRepository {

    private final FeedbackJpaRepository jpaRepository;

    public FeedbackRepositoryImpl(FeedbackJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Feedback feedback) {
        System.out.println(">>> [DEBUG] Chamando jpaRepository.save no FeedbackRepositoryImpl");

        FeedbackEntity entity = FeedbackMapper.toEntity(feedback);

        System.out.println("SALVANDO FEEDBACK ENTITY: " + entity);

        jpaRepository.save(entity);
    }

    @Override
    public Optional<Feedback> findById(FeedbackId id) {
        return jpaRepository.findById(id.getValue())
                .map(FeedbackMapper::toDomain);
    }

    @Override
    public List<Feedback> findByReceivedBy(MemberId memberId) {
        return jpaRepository.findByReceivedBy(memberId.getValue())
                .stream()
                .map(FeedbackMapper::toDomain)
                .collect(Collectors.toList());
    }
}
