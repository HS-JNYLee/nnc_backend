package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.testkmj.request.CreateUserRequest;
import com.eastflag.nnc.testkmj.request.DeleteUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserRequest;
import com.eastflag.nnc.testkmj.request.UpdateUserSettingRequest;
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
public class UserService {
    // 해싱을 위한 것이라고 추정
    //private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final UserAccountService userAccountService; // 미완
    private final UserSettingService userSettingService; // 미완

    /**
     * 유저 Entity를 생성하는 함수
     *
     * @param request UserController.createUser API에서 가져온 유저 생성 정보
     */
    public User createUser(CreateUserRequest request) {
        // request 정보들 각 userAccount, userSetting으로 분기.
        UserAccount userAccount = userAccountService.createUserAccount(request);
        UserSetting userSetting = null;

        RoleId roleId;
        if(request.getCaregiverId() != null) roleId = RoleId.CARETAKER;
        else {
            userSetting = userSettingService.createUserSetting();
            roleId = RoleId.CAREGIVER;
        }
        var user = User.builder()
                .name(request.getName())
                .telNum(request.getTelNum())
                .roleId(roleId)
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

        userSettingService.deleteUserSetting(userId);
        userAccountService.deleteUserAccount(userId);
        userRepository.deleteById(user.getUserId());
    }

    public User updateUser(UpdateUserRequest request) {
//        var beforeUser = userRepository
//                .findById(request.getUserId())
//                .orElseThrow(() -> new RuntimeException(request.getUserId() + "를 찾을 수 없음."));
//
//        UserAccount userAccount = userAccountService.updateUserAccount(request);
//        beforeUser.setUserAccount(userAccount);
//
//        var user = User.builder()
//                .userId(beforeUser.getUserId())
//                .name(request.getName() != null ? request.getName() : beforeUser.getName())
//                .telNum(request.getTelNum() != null ? request.getTelNum() : beforeUser.getTelNum())
//                .roleId(beforeUser.getRoleId())
//                .userAccount(userAccount)
//                .userSetting(beforeUser.getUserSetting())
//                .build();

        var user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(request.getUserId() + "를 찾을 수 없음."));

        if(request.getName() != null) user.setName(request.getName());
        if(request.getTelNum() != null) user.setTelNum(request.getTelNum());

        UserAccount userAccount = userAccountService.updateUserAccount(request);
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

    public User getUser(int userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException(userId + "를 찾을 수 없음."));
        return user;
    }
}
