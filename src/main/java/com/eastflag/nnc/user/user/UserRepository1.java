package com.eastflag.nnc.user.user;

import com.eastflag.nnc.user.useraccount.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * User Table 접근 인터페이스
 */
public interface UserRepository1 extends JpaRepository<User1, Integer> {
    Optional<User1> findByUserAccount(UserAccount userAccount);
    Optional<User1> findByTelNum(String telNum);
    Optional<List<User1>> findByRole1(Role1 role1);
}