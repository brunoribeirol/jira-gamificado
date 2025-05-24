package com.cesar.school.infrastructure.persistence.mapper.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.vo.ChallengeId;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.infrastructure.persistence.entity.projectmanagement.ChallengeEntity;

public class ChallengeMapper {

    public static ChallengeEntity toEntity(Challenge domain) {
        Integer id = domain.getId() != null ? domain.getId().getValue() : null;
        return new ChallengeEntity(
                id,
                domain.getTitle(),
                domain.getDescription(),
                domain.getCriteria(),
                domain.getExtraPoints(),
                domain.getCreatedBy().getValue(),
                domain.getProjectId().getValue(),
                domain.getDeadline()
        );
    }

    public static Challenge toDomain(ChallengeEntity entity) {
        return new Challenge(
                new ChallengeId(entity.getId()),
                entity.getTitle(),
                entity.getDescription(),
                entity.getCriteria(),
                entity.getExtraPoints(),
                new MemberId(entity.getCreatedBy()),
                new ProjectId(entity.getProjectId()),
                entity.getDeadline()
        );
    }
}
