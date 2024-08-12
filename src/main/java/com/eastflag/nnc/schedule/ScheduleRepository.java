package com.eastflag.nnc.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findScheduleByUserId(@Param("user_id") int user_id);
    List<Schedule> findScheduleByDateBegin(@Param("date_begin")String dateBegin);
}

//2024-08-10 16:45:15.248 임시 삭제 X