package com.eastflag.nnc.elevator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ElevatorRepository extends JpaRepository<Elevator, Long>  {
    Optional<List<Elevator>> findElevatorsByStationNameContaining(String stationName);
    Optional<List<Elevator>> findByStationName(String stationName);
    Integer deleteByElevatorId(@Param("elevator_id") int elevatorId);
}
