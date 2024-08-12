package com.eastflag.nnc.fcm;

import com.eastflag.nnc.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("/api/v1/fcm")
@RequiredArgsConstructor
public class FcmController {

    private final FcmService fcmService;

    @PostMapping()
    public ResponseEntity<CommonResponse> fcm(@RequestBody MessageWrapper messageWrapper) throws IOException {
        return ResponseEntity.ok(fcmService.postMessageCareGiver(messageWrapper));
    }
}
