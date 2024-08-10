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

    /**
     * 긴급전화 조회 : 고유 ID
     * @param emergencyId
     * @return
     */
    @GetMapping("/{emergency_id}")
    public ResponseEntity<CommonResponse> getEmergencyByEmergencyId(@PathVariable("emergency_id") Integer emergencyId) {
        return ResponseEntity.ok(emergencyService.getEmergencyByEmergencyId(emergencyId));
    }

    /**
     * 긴급전화 추가
     * @param emergency
     * @return
     */
    @PostMapping()
    public ResponseEntity<CommonResponse> insertEmergency(@RequestBody Emergency emergency) {
        return ResponseEntity.ok(emergencyService.addEmergency(emergency));
    }

    /**
     * 긴급전화 삭제
     * @param emergency
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<CommonResponse> deleteEmergency(@RequestBody Emergency emergency) {
        return ResponseEntity.ok(emergencyService.deleteEmergency(emergency));
    }

    /**
     * 긴급전화 수정
     * @param emergency
     * @return
     */
    @PatchMapping()
    public ResponseEntity<CommonResponse> updateEmergency(@RequestBody Emergency emergency) {
        return ResponseEntity.ok(emergencyService.updateEmergency(emergency));
    }

    /**
     * 긴급전화 조회 : 사용자 ID, 전회번호
     * @param userId
     * @param telNum
     * @return
     */
    @GetMapping("/{user_id}/{tel_num}")
    public ResponseEntity<CommonResponse> getEmergencyByUserIdAndTelNum(@PathVariable("user_id") Integer userId, @PathVariable("tel_num") String telNum) {
        return ResponseEntity.ok(emergencyService.getEmergencyByUserIdAndTelNum(userId, telNum));
    }

    /**
     * 특정 사용자의 긴급전화 리스트 조회
     * @param userId
     * @return
     */
    @GetMapping("{user_id}/list")
    public ResponseEntity<CommonResponse> getEmergencyList(@PathVariable("user_id") Integer userId) {
        return ResponseEntity.ok(emergencyService.getAllEmergency(userId));
    }
}
