package com.eastflag.nnc.user1.user;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.exception.ControlledException;
import com.eastflag.nnc.user1.request.CreateUserRequest;
import com.eastflag.nnc.user1.request.LoginRequest;
import com.eastflag.nnc.user1.request.UpdateUserRequest;
import com.eastflag.nnc.user1.request.UpdateUserSettingRequest;
import com.eastflag.nnc.user1.useraccount.UserAccountService;
import com.eastflag.nnc.user1.usersetting.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.eastflag.nnc.exception.errorcode.User1ErrorCode.NOT_CAREGIVER;

/**
 * User1, UserAccount, UserSetting과 관련된 API가 관리되는 Controller
 * ※ User라는 객체의 중복으로 User1로 명명함. (중복된 클래스 명은 전부 1로 표기하였음.)
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController1 {
    private final UserService1 userService;
    private final UserAccountService userAccountService;
    private final UserSettingService userSettingService;

    /**
     * User1 생성
     *
     * @param request 생성할 유저의 정보
     * @return 성공: 200
     */
    @PostMapping("/createUser")
    public CommonResponse createUser(
            @RequestBody CreateUserRequest request
    ) {
        var user1 = userService.createUser(request);
        return CommonResponse.builder().code(200).message(request.getName()+"(name) 생성 성공").data(user1).build();
    }

    /**
     * User1 삭제
     *
     * @param userId 삭제할 User1 Id
     * @return 성공: 200
     */
    @DeleteMapping("/deleteUser/{userId}")
    public CommonResponse deleteUser(
            @PathVariable int userId
    ) {
        userService.deleteUser(userId);
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").build();
    }

    /**
     * User1과 UserAccount 정보 업데이트
     *
     * @param request 수정할 user1, user_account 정보
     * @return 성공: 200
     */
    @PatchMapping("/updateUser")
    public CommonResponse updateUser(
            @RequestBody UpdateUserRequest request
    ) {
        var user1 = userService.updateUser(request);
        return CommonResponse.builder().code(200).message(request.getUserId() + ": 객제 전달 성공").data(user1).build();
    }

    /**
     * UserSetting 정보 업데이트
     *
     * @param request 수정할 userSetting 정보
     * @return 성공: 200
     */
    @PatchMapping("/updateUserSetting")
    public CommonResponse updateUserSetting(
            @RequestBody UpdateUserSettingRequest request
    ){
        var userSettingId = userService.getUserSettingId(request.getUserId());
        var userSetting = userSettingService.updateUserSetting(userSettingId, request);
        return CommonResponse.builder().code(200).message(request.getUserId() + ": 객제 전달 성공").data(userSetting).build();
    }

    /**
     * User1 정보 전달
     *
     * @param userId 전달받을 User1 Id
     * @return 성공: 200
     */
    @GetMapping("/getUser/{userId}")
    public CommonResponse getUser(
            @PathVariable int userId
    ){
        var user = userService.getUser(userId);
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").data(user).build();
    }

    /**
     * UserAccount 정보 전달
     *
     * @param userId 전달받을 User1 Id
     * @return 성공: 200
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
     * UserAccount 정보 전달
     *
     * @param caregiverEmail 인증할 보호자 Id
     * @return 성공: 200
     */
    @GetMapping("/getLinkPermission/{caregiverEmail}")
    public CommonResponse getLinkPermission(
            @PathVariable String caregiverEmail
    ){
        var userAccount = userAccountService.getUserAccount(caregiverEmail);
        var caregiverUser1 =  userService.getUser(userAccount);

        // 보호자 계정이 아님
        if(caregiverUser1.getRole1() != Role1.CAREGIVER) throw new ControlledException(NOT_CAREGIVER);

        var caregiverUserId = caregiverUser1.getUserId();
        // TODO: 보호자 승인 허가 전송하는 팝업 알림 전송
        // TODO: ※ (2024-08-12) 현재는 다이렉트로 전달 중인데, 완성 후에는 .data(userAccountId)생략할 것
        return CommonResponse.builder().code(200).message(caregiverUserId + ": 허가 요청 알림 전달 성공").data(caregiverUserId).build();
    }

    /**
     * UserSetting 정보를 전달한다.
     *
     * @param userId 전달받을 User1 Id
     * @return 성공: 200
     */
    @GetMapping("/getUserSetting/{userId}")
    public CommonResponse getUserSetting(
            @PathVariable int userId
    ){
        var userSettingId = userService.getUserSettingId(userId);
        var userSetting = userSettingService.getUserSetting(userSettingId);
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").data(userSetting).build();
    }

    /**
     * User1 Login
     * 
     * @param request 로그인할 User1의 email, password
     * @return 로그인 된 User1 객체 ※ 로그인 실패 시 401 코드와 함께 깡통 객체 전달
     */
    @PostMapping("/login")
    public CommonResponse login(@RequestBody LoginRequest request) {
        var user = userService.login(request.getEmail(), request.getPassword());

        if(user == null) return CommonResponse.builder().code(401).message(request.getEmail() + "로그인 실패").data(new User1()).build();
        else return CommonResponse.builder().code(200).message(request.getEmail() + ": 로그인 성공").data(user).build();
    }

    /**
     * 계정 생성 전화번호 인증
     *
     * @param telNum 존재하는지 확인 할 연락처
     * @return 존재하는 경우 200 존재하지 않는 경우 201
     */
    @GetMapping("/signupTelNum/{telNum}")
    public CommonResponse verificatonTelNum(
            @PathVariable String telNum
    ) {
        userService.getUser(telNum);
        return CommonResponse.builder().code(200).message(telNum + "은 존재하는 전화번호입니다.").build();
    }

    /**
     * 계정 생성 이메일 인증
     *
     * @param email 존재하는지 확인 할 이메일
     * @return 존재하는 경우 200 존재하지 않는 경우 201
     */
    @GetMapping("/signupEmail/{email}")
    public CommonResponse verificatonEmail(
            @PathVariable String email
    ){
        userAccountService.getUserAccount(email);
        return CommonResponse.builder().code(200).message(email + "은 존재하는 이메일입니다.").build();
    }
}
