package com.eastflag.nnc.testkmj.fcm;

import com.eastflag.nnc.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fcm1")
@RequiredArgsConstructor
public class FcmController1 {
    private final FcmService1 fcmService;

    // TODO: 회원가입 시 Fcm을 생성할 것이므로 잠성 이용 중단
    @PostMapping("/createFcm")
    public CommonResponse createFcm(
            @RequestBody FcmRequest1 request
    ) {
        fcmService.createFcm(request);
        return CommonResponse.builder().code(200).message(request.getUserId()+"(name) token 생성 성공").build();
    }

    @PatchMapping("/updateFcm")
    public CommonResponse updateFcm(
            @RequestBody FcmRequest1 request
    ) {
        fcmService.updateFcm(request);
        return CommonResponse.builder().code(200).message(request.getUserId()+"(name) token 변경 성공").build();
    }

    @PostMapping("/sendMessage")
    public CommonResponse sendMessage(
            @RequestBody AnotherUserSendMessageRequest request
    ){
        fcmService.anotherUserSendMessage(request);
        return CommonResponse.builder().code(200).message(request.getUserId()+"(name) 메세지 전달 성공").build();
    }
}
