package com.eastflag.nnc.testkmj.fcm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FcmRepository extends JpaRepository<Fcm, Integer> {
    Optional<Fcm> findByUserId(int userId);
    Optional<Fcm> findByFcmToken(String fcmToken);
}
