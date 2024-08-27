package com.eastflag.nnc.navigation;

import com.eastflag.nnc.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

@RestController
@RequestMapping("/api/v1/navigation")
@RequiredArgsConstructor
public class NavigationController {
    private final NavigationService navigationService;

    @PostMapping("/create/{caretakerId}")
    public CommonResponse createNavigation(@Path("caretakerId") int caretakerId, @RequestBody String transportRoute) {
        var navigation = navigationService.create(caretakerId, transportRoute);
        return CommonResponse.builder().code(200).message("내비게이션 생성 성공").data(navigation).build();
    }

    @GetMapping("/transportRoute/{caregiverId}")
    public CommonResponse getTransportRoute(@Path("caregiverId") int caregiverId) {
        var transportRoute = navigationService.getTransportRoute(caregiverId);
        return CommonResponse.builder().code(200).message("내비게이션 조회 성공").data(transportRoute).build();
    }

    @PatchMapping("route/{caretakerId}")
    public CommonResponse updateRoute(@Path("caretakerId") int caretakerId, @RequestBody String route) {
        navigationService.updateRoute(caretakerId, route);
        return CommonResponse.builder().code(200).message("경로 수정 성공").build();
    }

    @GetMapping("/route/{caregiverId}")
    public CommonResponse getRoute(@Path("caregiverId") int caregiverId) {
        var route = navigationService.getRoute(caregiverId);
        return CommonResponse.builder().code(200).message("경로 조회 성공").data(route).build();
    }

    @DeleteMapping("delete/{userId}")
    public CommonResponse deleteRoute(@Path("userId") int userId) {
        navigationService.deleteNavigation(userId);
        return CommonResponse.builder().code(200).message("내비게이션 삭제 완료").build();
    }
}
