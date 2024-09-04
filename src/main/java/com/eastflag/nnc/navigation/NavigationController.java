package com.eastflag.nnc.navigation;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.exception.ControlledException;
import com.eastflag.nnc.fcm.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.eastflag.nnc.exception.errorcode.FcmErrorCode.FCM_IO_EXCEPTION;

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
     * 생성 후 Relation된 상대방에게 안내시작 알림을 전달한다.
     *
     * @param userId 생성할 이용자의 정보
     * @param transportRoute 경로에 대한 정보가 표시되는 객체 Json
     *
     * @return 성공: 200 / 실패: 502
     * # FCM_IO_EXCEPTION
     */
    @PostMapping("/create/{userId}")
    public CommonResponse createNavigation(@PathVariable("userId") int userId, @RequestBody String transportRoute) {
        var navigation = navigationService.create(userId, transportRoute);
        String title = "시작/startNavigation";
        String body = "사용자가 navigation을 시작했습니다.";

        // 상대방에게 알림 전송 TODO: 유저 타입에 따라서, 전달하는 정보가 달라져야 할 것 같다.
        try {fcmService.send(userId, title, body);}
        catch (IOException e) {throw new ControlledException(FCM_IO_EXCEPTION);}

        return CommonResponse.builder().code(200).message("내비게이션 생성 성공").data(navigation).build();
    }

    /**
     * transportRoute 정보 전달
     *
     * @param caregiverId 전달받을 이용자 ID TODO: UserId 이용자로 수정할 것
     *
     * @return 성공: 200 / 실패: 404
     * # RELATION_ID_NOT_FOUND
     */
    @GetMapping("/transportRoute/{caregiverId}")
    public CommonResponse getTransportRoute(@PathVariable("caregiverId") int caregiverId) {
        var transportRoute = navigationService.getTransportRoute(caregiverId);
        return CommonResponse.builder().code(200).message("내비게이션 조회 성공").data(transportRoute).build();
    }

    /**
     * route 정보 업데이트
     * 
     * @param caretakerId 경로를 재탐색한 사용자 ID
     * @param route 경로 좌표 리스트 객체 Json
     *
     * @return 성공: 200 / 실패: 404
     * # FCM_IO_EXCEPTION
     */
    @PatchMapping("route/{caretakerId}")
    public CommonResponse updateRoute(@PathVariable("caretakerId") int caretakerId, @RequestBody String route) {
        navigationService.updateRoute(caretakerId, route);
        String title = "재탐색/devateRoute";
        String body = "사용자가 이탈하여 경로를 재탐색했습니다.";

        try {fcmService.send(caretakerId, title, body);}
        catch (IOException e) {throw new ControlledException(FCM_IO_EXCEPTION);}

        return CommonResponse.builder().code(200).message("경로 수정 성공").build();
    }

    /**
     * route 정보 전달
     *
     * @param caregiverId 전달 받을 보호자 ID
     *
     * @return 성공: 200 / 실패 404
     * # CAREGIVER_ID_NOT_FOUND
     */
    @GetMapping("/route/{caregiverId}")
    public CommonResponse getRoute(@PathVariable("caregiverId") int caregiverId) {
        var route = navigationService.getRoute(caregiverId);
        return CommonResponse.builder().code(200).message("경로 조회 성공").data(route).build();
    }

    /**
     * Navigation 삭제
     *
     * @param userId 삭제할 Navigation의 이용자 ID
     * @return 성공: 200 / 실패 404, 502
     * # RELATION_ID_NOT_FOUND
     * # FCM_IO_EXCEPTION
     */
    @DeleteMapping("delete/{userId}")
    public CommonResponse deleteRoute(@PathVariable("userId") int userId) {
        navigationService.deleteNavigation(userId);
        String title = "종료/endNavigation";
        String body = "경로안내를 종료했습니다.";

        try {fcmService.send(userId, title, body);}
        catch (IOException e) {throw new ControlledException(FCM_IO_EXCEPTION);}

        return CommonResponse.builder().code(200).message("내비게이션 삭제 완료").build();
    }
}
