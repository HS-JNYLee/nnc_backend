package com.eastflag.nnc.user.userrelation;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRelation Table 접근 인터페이스
 */
public interface UserRelationRepository extends JpaRepository<UserRelation, Integer> {
    @Transactional
    Optional<UserRelation> findByCaretakerId(int caretakerId);
    Optional<UserRelation> findByCaregiverId(int caregiverId);
}
