package com.eastflag.nnc.emergency;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    Optional<List<Emergency>> findByUserId(@Param("user_id") int id);
    Optional<List<Emergency>> findByUserIdAndBookmarkYn(Integer userId, String bookmarkYn);
    Optional<Emergency> findByEmergencyId(@Param("emergency_id") int emergencyId);
    @Transactional
    Optional<Integer> deleteByEmergencyId(@Param("emergency_id") int emergencyId);
    Optional<Emergency> findByUserIdAndTelNum(@Param("user_id") int user_id, @Param("tel_num") String tel_num);
}
