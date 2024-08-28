package com.eastflag.nnc.elevator;

import com.eastflag.nnc.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Body;

@Log4j2
@RestController
@RequestMapping("/api/v1/elevator")
@RequiredArgsConstructor
public class ElevatorController {
    private final ElevatorService elevatorService;

    @GetMapping("/{station_name}")
    public ResponseEntity<CommonResponse> getElevatorLocation(@PathVariable("station_name")String stationName){
        return ResponseEntity.ok(elevatorService.getElevatorLocation(stationName));
    }

    @PostMapping()
    public ResponseEntity<CommonResponse> addElevatorLocation(@RequestBody Elevator insert){
        return ResponseEntity.ok(elevatorService.addElevator(insert));
    }

//    @DeleteMapping("{elevator_id}")
//    public ResponseEntity<CommonResponse> deleteElevator(@PathVariable("elevator_id") int elevId){
//        return ResponseEntity.ok(elevatorService.deleteElevator(elevId));
//    }

}
