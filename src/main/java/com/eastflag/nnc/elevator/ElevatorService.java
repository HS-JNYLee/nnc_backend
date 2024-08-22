package com.eastflag.nnc.elevator;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElevatorService {
    @Autowired
    private final ElevatorRepository elevatorRepository;

    public ElevatorService(ElevatorRepository elevatorRepository) {
        this.elevatorRepository = elevatorRepository;
    }

    public CommonResponse getElevators(String stationName) {
        Optional<List<Elevator>> elevators = elevatorRepository.findElevatorsByStationNameContaining(stationName);
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(elevators)
                .build();
    }

    public CommonResponse addElevator(Elevator elevator) {
        int id = elevatorRepository.save(elevator).getElevatorId();
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(id)
                .build();
    }
}
