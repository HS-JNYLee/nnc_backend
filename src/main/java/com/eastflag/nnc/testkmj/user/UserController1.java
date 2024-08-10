package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.testkmj.request.CreateUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserSettingRequest;
import com.eastflag.nnc.testkmj.useraccount.UserAccountService;
import com.eastflag.nnc.testkmj.usersetting.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController1 {
    private final UserService1 userService;
    private final UserAccountService userAccountService;
    private final UserSettingService userSettingService;

    /**
     * user를 생성하는 API
     *
     * @param request 생성할 유저의 정보
     * @return
     */
    @PostMapping("/createUser")
    public CommonResponse createUser(
            @RequestBody CreateUserRequest request
    ) {
        userService.createUser(request);
        return CommonResponse.builder().code(200).message(request.getName()+"(name) 생성 성공").build();
    }

    /**
     * user를 삭제하는 API
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteUser/{userId}")
    public CommonResponse deleteUser(
            @PathVariable int userId
    ) {
        userService.deleteUser(userId);
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").build();
    }

    /**
     * user와 userAccount 정보를 업데이트 한다.
     *
     * @param request update 할 user, user_account 정보
     * @return
     */
    @PatchMapping("/updateUser")
    public CommonResponse updateUser(
            @RequestBody UpdateUserRequest request
    ) {
        userService.updateUser(request);
        return CommonResponse.builder().code(200).message(request.getUserId() + ": 객제 전달 성공").build();
    }

    /**
     * userSetting 정보를 업데이트 한다.
     *
     * @param request update 할 userSetting 정보
     * @return
     */
    @PatchMapping("/updateUserSetting")
    public CommonResponse updateUserSetting(
            @RequestBody UpdateUserSettingRequest request
    ){
        var userSettingId = userService.getUserSettingId(request.getUserId());
        userSettingService.updateUserSetting(userSettingId, request);
        return CommonResponse.builder().code(200).message(request.getUserId() + ": 객제 전달 성공").build();
    }

    /**
     * user 정보를 전달하는 코드
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUser/{userId}")
    public CommonResponse getUser(
            @PathVariable int userId
    ){
        var user = userService.getUser(userId);
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").data(user).build();
    }

    @GetMapping("/getUser/{userId}/{field}")
    public CommonResponse getUserField(
            @PathVariable int userId, @PathVariable UserField field
    ){
        var user = userService.getUser(userId);
        switch(field){
            case NAME -> {
                return CommonResponse.builder().code(200).message(userId+"(name) 전달 성공").data(user.getName()).build();
            }
            case TEL_NUM -> {
                return CommonResponse.builder().code(200).message(userId+"(tel_num) 전달 성공").data(user.getTelNum()).build();
            }
            case ROLE_ID -> {
                return CommonResponse.builder().code(200).message(userId+"(role_id) 전달 성공").data(user.getRole()).build();
            }
            case USER_SETTING -> {
                return CommonResponse.builder().code(200).message(userId+"(user_setting) 전달 성공").data(user.getUserSetting()).build();
            }
            case USER_ACCOUNT -> {
                return CommonResponse.builder().code(200).message(userId+"(user_account) 전달 성공").data(user.getUserAccount()).build();
            }
            default -> {
                // TODO: 에러코드 삽입할 것
                return null;
            }
        }
    }

    /**
     * userAccount 정보를 전달하는 코드
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserAccount/{userId}")
    public CommonResponse getUserAccount(
            @PathVariable int userId
    ){
        var userAccountId = userService.getUserAccountId(userId);
        var userAccount = userAccountService.getUserAccount(userAccountId);
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").data(userAccount).build();
    }

    /**
     * userSetting 정보를 전달하는 코드
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserSetting/{userId}")
    public CommonResponse getUserSetting(
            @PathVariable int userId
    ){
        var userSettingId = userService.getUserSettingId(userId);
        var userSetting = userSettingService.getUserSetting(userSettingId);
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").data(userSetting).build();
    }
}
