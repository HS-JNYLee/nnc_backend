package com.eastflag.nnc.testkmj.useraccount;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserAccount Table 접근 인터페이스
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    // 사용하진 않는데 가능해서 만들어둠
    // Optional<UserAccount> findByEmail(String email);
}