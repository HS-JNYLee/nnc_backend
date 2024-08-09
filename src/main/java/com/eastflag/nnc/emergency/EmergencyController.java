package com.eastflag.nnc.emergency;

import com.eastflag.nnc.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Log4j2
@RestController
@RequestMapping("/api/v1/emergency")
@RequiredArgsConstructor
public class EmergencyController {
    private final EmergencyService emergencyService;

    @GetMapping("/{user_id}")
    public ResponseEntity<Emergency> getEmergency(@PathVariable("user_id") Integer userId) {
        Emergency returnValue = new Emergency();
        try {
            returnValue = emergencyService.getEmergencyByUserId(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CommonResponse> insertEmergency(@RequestBody Emergency emergency) {
        return ResponseEntity.ok(emergencyService.addEmergency(emergency));
    }

    @DeleteMapping()
    public ResponseEntity<CommonResponse> deleteEmergency(@RequestBody Emergency emergency) {
        return ResponseEntity.ok(emergencyService.deleteEmergency(emergency));
    }

    @PatchMapping()
    public ResponseEntity<CommonResponse> updateEmergency(@RequestBody Emergency emergency) {
        return ResponseEntity.ok(emergencyService.updateEmergency(emergency));
    }
}
