package com.eastflag.nnc.elevator;

import com.eastflag.nnc.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/v1/elevator")
@RequiredArgsConstructor
public class ElevatorController {
    private final ElevatorService elevatorService;

    @GetMapping()
    public ResponseEntity<CommonResponse> getElevators(@RequestParam("stationName") String stationName) {
        return ResponseEntity.ok(elevatorService.getElevators(stationName));
    }

    @PostMapping()
    public ResponseEntity<CommonResponse> addElevator(@RequestBody Elevator elevator) {
        return ResponseEntity.ok(elevatorService.addElevator(elevator));
    }
}
