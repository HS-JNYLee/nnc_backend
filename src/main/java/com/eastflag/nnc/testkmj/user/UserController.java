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

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(
        @RequestBody @PathVariable int userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    /**
     * user와 userAccount 정보를 업데이트 한다.
     *
     * @param request update 할 user, user_account 정보
     * @return
     */
    @PatchMapping("/updateUser")
    public ResponseEntity<?> updateUser(
        @RequestBody UpdateUserRequest request
    ) {
      userService.updateUser(request);
      return ResponseEntity.ok().build();
    }

    /**
     * userSetting 정보를 업데이트 한다.
     *
     * @param request update 할 userSetting 정보
     * @return
     */
    @PatchMapping("/updateUserSetting")
    public ResponseEntity<?> updateUserSetting(
        @RequestBody UpdateUserSettingRequest request
    ){
        userSettingService.updateUserSetting(request);
        return ResponseEntity.ok().build();
    }


}
