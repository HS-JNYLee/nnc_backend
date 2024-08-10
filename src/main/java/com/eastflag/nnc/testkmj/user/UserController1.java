package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.testkmj.relation.UserRelationRepository;
import com.eastflag.nnc.testkmj.relation.UserRelationService;
import com.eastflag.nnc.testkmj.request.CreateUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserSettingRequest;
import com.eastflag.nnc.testkmj.useraccount.UserAccountService;
import com.eastflag.nnc.testkmj.usersetting.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    private final UserRelationRepository userRelationRepository;

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
        userService.createUser(request);
        return CommonResponse.builder().code(200).message(request.getName()+"(name) 생성 성공").build();
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
        userService.updateUser(request);
        return CommonResponse.builder().code(200).message(request.getUserId() + ": 객제 전달 성공").build();
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
        userSettingService.updateUserSetting(userSettingId, request);
        return CommonResponse.builder().code(200).message(request.getUserId() + ": 객제 전달 성공").build();
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
     * UserRelation 정보를 전달한다.
     *
     * @param userId 전달받을 User1 Id
     * @return 성공: 200
     */
    @GetMapping("/getUserRelation/{userId}")
    public CommonResponse getUserRelation(
            @PathVariable int userId
    ) {
        var userRelation = userRelationRepository.findByCaretakerId(userId).orElseGet(() -> userRelationRepository
                .findByCaregiverId(userId)
                .orElseThrow(() -> new RuntimeException(userId + "에 대한 관계 설정이 없음")));
        return CommonResponse.builder().code(200).message(userId + ": 객제 전달 성공").data(userRelation).build();
    }
}
