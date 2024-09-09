package com.eastflag.nnc.fcm;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.fcm.request.FcmRequest;
import com.eastflag.nnc.fcm.request.MessageWrapper;
import com.eastflag.nnc.user.userrelation.UserRelationService;
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
    private final UserRelationService userRelationService;
    private final FcmService fcmService;

    @PostMapping()
    public ResponseEntity<CommonResponse> fcm(@RequestBody MessageWrapper messageWrapper) throws IOException {
        return ResponseEntity.ok(fcmService.postMessage(messageWrapper));
    }

    /**
     * fcm1을 통해 relation 상대방에게 메세지를 전송
     *
     * @param userId 메세지를 전송한 이용자 userId
     * @param messageWrapper 메세지 전송에 필요한 정보 객체
     *
     * @return 성공: 200 / 실패: 404, 502
     * # RELATION_USER_ID_NOT_FOUND
     * # FCM_USER_ID_NOT_FOUND
     * # FCM_IO_EXCEPTION
     *
     * @throws IOException TODO: 삭제할 것
     */
    @PostMapping("/send/{userId}")
    public ResponseEntity<CommonResponse> send(@PathVariable int userId, @RequestBody MessageWrapper messageWrapper) throws IOException {
        var anotherUserId = userRelationService.getAnotherUserId(userId);
        messageWrapper.getMessage().setToken(fcmService.getToken(anotherUserId));
        return ResponseEntity.ok(fcmService.postMessage(messageWrapper));
    }

    /**
     * fcm1 생성
     * @param request 생성할 fcm 정보
     *
     * @return 성공: 200
     * TODO: 회원가입 시 Fcm을 생성할 것이므로 잠정 이용 금지
     */
    @PostMapping("/createFcm")
    public CommonResponse createFcm(
            @RequestBody FcmRequest request
    ) {
        fcmService.createFcm(request);
        return CommonResponse.builder().code(200).message(request.getUserId()+"(name) token 생성 성공").build();
    }

    /**
     * fcm1 정보 업데이트
     * @param request 변경할 fcm 정보
     * 
     * @return 성공: 200 / 실패: 404
     * # FCM_USER_ID_NOT_FOUND
     */
    @PatchMapping("/updateFcm")
    public CommonResponse updateFcm(
            @RequestBody FcmRequest request
    ) {
        fcmService.updateFcm(request);
        return CommonResponse.builder().code(200).message(request.getUserId()+"(name) token 변경 성공").build();
    }
}
