package com.eastflag.nnc.navigation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * navigation Table 접근 인터페이스
 */
public interface NavigationRepository extends JpaRepository<Navigation, Integer> {
    Optional<Navigation> findByCaregiverId(int caregiverId);
    Optional<Navigation> findByCaretakerId(int caretakerId);
}
