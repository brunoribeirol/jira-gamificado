package com.cesar.school.infrastructure.persistence.springdata.teamsmembers;

import com.cesar.school.infrastructure.persistence.entity.teamsmembers.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Integer> {
}