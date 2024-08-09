package com.eastflag.nnc.testkmj.User;

import com.eastflag.nnc.testkmj.UserAccount.UserAccountService;
import com.eastflag.nnc.testkmj.UserSetting.UserSettingService;
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
    private final UserAccountService userAccountService;
    private final UserSettingService userSettingService;

    /*@PatchMapping
    public ResponseEntity<?> createUser(
        @RequestBody CreateUserRequest request,
        Principal connectedUser
    ) {
        userService.createUser(request, connectedUser);
        return ResponseEntity.ok().build();
    }*/
}
