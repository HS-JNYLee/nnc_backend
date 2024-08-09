package com.eastflag.nnc.testkmj.UserAccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    // 사용하진 않는데 가능해서 만들어둠
    // Optional<UserAccount> findByEmail(String email);
}
