package com.eastflag.nnc.testkmj.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 사용하진 않는데 가능해서 만들어둠
    // Optional<User> findByTelNum(String email);
}
