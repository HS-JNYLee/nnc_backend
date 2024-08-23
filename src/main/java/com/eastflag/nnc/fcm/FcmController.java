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

    // TODO: 회원가입 시 Fcm을 생성할 것이므로 잠성 이용 금지
    @PostMapping("/createFcm")
    public CommonResponse createFcm(
            @RequestBody FcmRequest request
    ) {
        fcmService.createFcm(request);
        return CommonResponse.builder().code(200).message(request.getUserId()+"(name) token 생성 성공").build();
    }

    @PatchMapping("/updateFcm")
    public CommonResponse updateFcm(
            @RequestBody FcmRequest request
    ) {
        fcmService.updateFcm(request);
        return CommonResponse.builder().code(200).message(request.getUserId()+"(name) token 변경 성공").build();
    }
}
