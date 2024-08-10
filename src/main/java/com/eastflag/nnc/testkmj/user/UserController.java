package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.testkmj.request.CreateUserRequest;
import com.eastflag.nnc.testkmj.request.DeleteUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserSettingRequest;
import com.eastflag.nnc.testkmj.usersetting.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserSettingService userSettingService;

    /**
     * 유저를 생성하는 API
     *
     * @param request 생성할 유저의 정보
     * @return
     */
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(
        @RequestBody CreateUserRequest request
    ) {
        userService.createUser(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(
        @RequestBody DeleteUserRequest request
    ) {
        userService.deleteUser(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/updateUser")
    public ResponseEntity<?> updateUser(
        @RequestBody UpdateUserRequest request
    ) {
      userService.updateUser(request);
      return ResponseEntity.ok().build();
    }

    // userSetting을 접근 하기 위해 userService를 접근하는게 맞나 의문
    @PatchMapping("/updateUserSetting")
    public ResponseEntity<?> updateUserSetting(
        @RequestBody UpdateUserSettingRequest request
    ){
        userSettingService.updateUserSetting(request);
        return ResponseEntity.ok().build();
    }
}
