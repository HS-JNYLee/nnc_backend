package com.eastflag.nnc.elevator;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.eastflag.nnc.exception.ControlledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.eastflag.nnc.exception.errorcode.ElevatorErrorCode.*;

@Service
public class ElevatorService {
    @Autowired
    private final ElevatorRepository elevatorRepository;

    public ElevatorService(ElevatorRepository elevatorRepository) {
        this.elevatorRepository = elevatorRepository;
    }

    public CommonResponse getElevatorLocation(String stationName){

        List<Elevator> res = elevatorRepository.findByStationName(stationName).orElse(Collections.emptyList());

        if(res.isEmpty()) throw new ControlledException(SUBWAY_NAME_NOT_EXIST);

        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(res)
                .build();
    }

    public CommonResponse addElevator(Elevator e){
        elevatorRepository.save(e);

        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(e)
                .build();
    }

//    public CommonResponse deleteElevator(int id){
//        Integer ret = elevatorRepository.deleteByElevatorId(id);
//
//        if(ret==null) throw new ControlledException(ELEVATOR_ID_NOT_EXIST);
//
//        return CommonResponse.builder()
//                .code(200)
//                .message(ResponseMessage.SUCCESS)
//                .data(ret)
//                .build();
//    }

}
