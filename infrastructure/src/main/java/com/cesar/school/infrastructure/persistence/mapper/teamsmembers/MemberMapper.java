package com.cesar.school.infrastructure.persistence.mapper.teamsmembers;

import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.infrastructure.persistence.entity.teamsmembers.MemberEntity;
import com.cesar.school.infrastructure.persistence.entity.teamsmembers.RewardIdEmbeddable;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMapper {

    public static MemberEntity toEntity(Member domain) {
        MemberEntity entity = new MemberEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId().getValue());
        }
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setRole(domain.getRole());
        entity.setIndividualScore(domain.getIndividualScore());
        entity.setAdmin(domain.isAdmin());

        List<RewardIdEmbeddable> rewards = domain.getUnlockedRewardIds()
                .stream()
                .map(r -> new RewardIdEmbeddable(r.getValue()))
                .collect(Collectors.toList());
        entity.setUnlockedRewards(rewards);

        return entity;
    }

    public static Member toDomain(MemberEntity entity) {
        Member member = new Member(
                new MemberId(entity.getId()),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole(),
                entity.isAdmin()
        );
        member.addPoints(entity.getIndividualScore());

        entity.getUnlockedRewards()
                .forEach(r -> member.unlockReward(new RewardId(r.getRewardId())));

        return member;
    }
}
