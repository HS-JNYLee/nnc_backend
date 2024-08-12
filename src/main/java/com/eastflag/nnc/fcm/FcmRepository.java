package com.eastflag.nnc.fcm;

import com.eastflag.nnc.emergency.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FcmRepository extends JpaRepository<Emergency, Long> {
    List<Emergency> findByUserId(@Param("user_id") int id);
}
