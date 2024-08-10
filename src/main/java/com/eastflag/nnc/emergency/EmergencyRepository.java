package com.eastflag.nnc.emergency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    List<Emergency> findByUserId(@Param("user_id") int id);
    Emergency findByEmergencyId(@Param("emergency_id") int emergencyId);
    Optional<Emergency> findByUserIdAndTelNum(@Param("user_id") int user_id, @Param("tel_num") String tel_num);
}
