package com.eastflag.nnc.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Optional<List<Schedule>> findScheduleByUserId(@Param("user_id") int user_id);
    Optional<List<Schedule>> findScheduleByDateBegin(@Param("date_begin")String dateBegin);
}