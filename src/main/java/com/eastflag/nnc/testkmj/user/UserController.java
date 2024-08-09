package com.eastflag.nnc.testkmj.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 유저를 생성하는 API
     *
     * @param request 생성할 유저의 정보
     * @return
     */
    @PatchMapping
    public ResponseEntity<?> createUser(
        @RequestBody CreateUserRequest request
    ) {
        userService.createUser(request);
        return ResponseEntity.ok().build();
    }
}
