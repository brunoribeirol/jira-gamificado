package com.cesar.school.infrastructure.persistence.mapper.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.vo.ChallengeId;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.infrastructure.persistence.entity.projectmanagement.ChallengeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ChallengeMapper {

    public static ChallengeEntity toEntity(Challenge domain) {
        ChallengeEntity entity = new ChallengeEntity(); // Usando construtor padrão

        // Definir o ID apenas se a entidade de domínio já tiver um (para atualizações)
        if (domain.getId() != null) {
            entity.setId(domain.getId().getValue());
        }

        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCriteria(domain.getCriteria());
        entity.setExtraPoints(domain.getExtraPoints());
        entity.setCreatedBy(domain.getCreatedBy().getValue());
        entity.setProjectId(domain.getProjectId().getValue());
        entity.setDeadline(domain.getDeadline());
        
        // Note: Mapeamento dos assignees pode ser necessário se ChallengeEntity tiver relação ManyToMany
        // Com base na entidade Challenge de domínio, parece que não há assignees diretamente no Challenge.

        return entity;
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
