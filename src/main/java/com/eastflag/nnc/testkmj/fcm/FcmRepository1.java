package com.eastflag.nnc.testkmj.fcm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FcmRepository1 extends JpaRepository<Fcm1, Integer> {
    Optional<Fcm1> findByUserId(int userId);
    Optional<Fcm1> findByFcmToken(String fcmToken);
}
