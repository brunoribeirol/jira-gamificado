package com.cesar.school.infrastructure.persistence.springdata.teamsmembers;

import com.cesar.school.infrastructure.persistence.entity.teamsmembers.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Integer> {
    Optional<MemberEntity> findByEmail(String email);
}