package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.testkmj.Request.CreateUserRequest;
import com.eastflag.nnc.testkmj.Request.DeleteUserRequest;
import com.eastflag.nnc.testkmj.Request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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

    @PatchMapping("updateUser")
    public ResponseEntity<?> updateUser(
        @RequestBody UpdateUserRequest request
    ) {
      userService.updateUser(request);
      return ResponseEntity.ok().build();
    }
}
