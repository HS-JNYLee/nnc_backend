package com.eastflag.nnc.navigation;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.exception.ControlledException;
import com.eastflag.nnc.fcm.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.io.IOException;

import static com.eastflag.nnc.exception.errorcode.BasicErrorCode.FCM_IO_EXCEPTION;
import static com.eastflag.nnc.exception.errorcode.EmergencyErrorCode.EMERGENCY_USER_ID_TELNUM_NOT_FOUND;

/**
 * Navigation과 관련된 API가 관리되는 Controller
 *
 */
@Log4j2
@RestController
@RequestMapping("/api/v1/navigation")
@RequiredArgsConstructor
public class NavigationController {
    private final NavigationService navigationService;
    private final FcmService fcmService;

    /**
     * Navigation 생성
     *
     * @param userId 생성할 이용자의 정보
     * @param transportRoute 경로에 대한 정보가 표시되는 객체 Json
     *
     * @return 성공: 200
     * @throws IOException fcmService 전송 중 에러 발생
     */
    @PostMapping("/create/{userId}")
    public CommonResponse createNavigation(@PathVariable("userId") int userId, @RequestBody String transportRoute) {
        var navigation = navigationService.create(userId, transportRoute);
        String title = "시작/startNavigation";
        String body = "사용자가 navigation을 시작했습니다.";
        try {
            fcmService.send(userId, title, body);
        }
        catch (IOException e) {
            throw new ControlledException(FCM_IO_EXCEPTION);
        }
        return CommonResponse.builder().code(200).message("내비게이션 생성 성공").data(navigation).build();
    }

    @GetMapping("/transportRoute/{caregiverId}")
    public CommonResponse getTransportRoute(@PathVariable("caregiverId") int caregiverId) {
        var transportRoute = navigationService.getTransportRoute(caregiverId);
        return CommonResponse.builder().code(200).message("내비게이션 조회 성공").data(transportRoute).build();
    }

    @PatchMapping("route/{caretakerId}")
    public CommonResponse updateRoute(@PathVariable("caretakerId") int caretakerId, @RequestBody String route) {
        navigationService.updateRoute(caretakerId, route);
        String title = "재탐색/devateRoute";
        String body = "사용자가 이탈하여 경로를 재탐색했습니다.";

        try {
            fcmService.send(caretakerId, title, body);
        }
        catch (IOException e) {
            throw new ControlledException(FCM_IO_EXCEPTION);
        }

        return CommonResponse.builder().code(200).message("경로 수정 성공").build();
    }

    @GetMapping("/route/{caregiverId}")
    public CommonResponse getRoute(@PathVariable("caregiverId") int caregiverId) {
        var route = navigationService.getRoute(caregiverId);
        return CommonResponse.builder().code(200).message("경로 조회 성공").data(route).build();
    }

    @DeleteMapping("delete/{userId}")
    public CommonResponse deleteRoute(@PathVariable("userId") int userId) throws IOException {
        navigationService.deleteNavigation(userId);
        String title = "종료/endNavigation";
        String body = "경로안내를 종료했습니다.";
        fcmService.send(userId, title, body);
        return CommonResponse.builder().code(200).message("내비게이션 삭제 완료").build();
    }
}
