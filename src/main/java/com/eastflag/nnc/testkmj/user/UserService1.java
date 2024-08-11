package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.testkmj.request.CreateUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserRequest;
import com.eastflag.nnc.testkmj.useraccount.UserAccount;
import com.eastflag.nnc.testkmj.useraccount.UserAccountService;
import com.eastflag.nnc.testkmj.usersetting.UserSetting;
import com.eastflag.nnc.testkmj.usersetting.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 유저 관리 Service 클래스
 */
@Service
@RequiredArgsConstructor
public class UserService1 {
    // 해싱을 위한 것이라고 추정
    //private final PasswordEncoder passwordEncoder;

    private final UserRepository1 userRepository;
    private final UserAccountService userAccountService;
    private final UserSettingService userSettingService;

    /**
     * 유저 Entity를 생성하는 함수
     *
     * @param request UserController.createUser API에서 가져온 유저 생성 정보
     */
    public User1 createUser(CreateUserRequest request) {
        // request 정보들 각 userAccount, userSetting으로 분기.
        UserAccount userAccount = userAccountService.createUserAccount(request);
        UserSetting userSetting = userSettingService.createUserSetting();

        Role1 role;
        if(request.getCaregiverEmail() == null) role = Role1.CAREGIVER;
        else {
            // TODO: relation 추가해볼 것
            role = Role1.CARETAKER;
        }

        var user = User1.builder()
                .name(request.getName())
                .telNum(request.getTelNum())
                .role1(role)
                .userAccount(userAccount)
                .userSetting(userSetting)
                .build();

        userRepository.save(user);

        return user;
    }

    public void deleteUser(int userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException(userId + "를 찾을 수 없음."));

        var userSettingId = getUserSettingId(userId);
        var userAccountId = getUserAccountId(userId);
        userRepository.deleteById(user.getUserId());
        userSettingService.deleteUserSetting(userSettingId);
        userAccountService.deleteUserAccount(userAccountId);
    }

    public User1 updateUser(UpdateUserRequest request) {
        var user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(request.getUserId() + "를 찾을 수 없음."));

        if(request.getName() != null) user.setName(request.getName());
        if(request.getTelNum() != null) user.setTelNum(request.getTelNum());

        var userAccountId = getUserAccountId(request.getUserId());
        UserAccount userAccount = userAccountService.updateUserAccount(userAccountId, request);
        user.setUserAccount(userAccount);

        userRepository.save(user);

        return user;
    }

    public int getUserAccountId(int userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException(userId + "를 찾을 수 없음."));
        return user.getUserAccount().getUserAccountId();
    }

    public int getUserSettingId(int userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException(userId + "를 찾을 수 없음."));
        return user.getUserSetting().getUserSettingId();
    }

    public User1 getUser(int userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException(userId + "를 찾을 수 없음."));
        return user;
    }
}