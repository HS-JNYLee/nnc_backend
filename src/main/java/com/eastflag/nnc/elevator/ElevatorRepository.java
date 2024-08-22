package com.eastflag.nnc.elevator;

import com.eastflag.nnc.emergency.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElevatorRepository extends JpaRepository<Elevator, Long>  {
    Optional<List<Elevator>> findElevatorsByStationNameContaining(String stationName);
}
