package com.eastflag.nnc.navigation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NavigationRepository extends JpaRepository<Navigation, Integer> {
    Optional<Navigation> findByCaregiverId(int caregiverId);
    Optional<Navigation> findByCaretakerId(int caretakerId);
}
