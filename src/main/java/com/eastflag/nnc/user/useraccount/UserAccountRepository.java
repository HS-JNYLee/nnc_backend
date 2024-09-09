package com.eastflag.nnc.user.useraccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserAccount Table 접근 인터페이스
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    Optional<UserAccount> findByEmail(String email);
    Optional<UserAccount> findByEmailAndPassword(String email, String password);
}