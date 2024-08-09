package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.testkmj.useraccount.UserAccount;
import com.eastflag.nnc.testkmj.useraccount.UserAccountService;
import com.eastflag.nnc.testkmj.usersetting.UserSetting;
import com.eastflag.nnc.testkmj.usersetting.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

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
    public void createUser(CreateUserRequest request) {
        // request 정보들 각 userAccount, userSetting으로 분기.
        UserAccount userAccount = userAccountService.createUserAccount(request);
        UserSetting userSetting = userSettingService.createUserSetting();

        RoleId roleId;
        if(request.getCaregiverId() == null) roleId = RoleId.CAREGIVER; else roleId = RoleId.CARETAKER;
        var user = User.builder()
                .name(request.getName())
                .telNum(request.getTelNum())
                .roleId(roleId)
                .userAccount(userAccount)
                .userSetting(userSetting)
                .build();

        userRepository.save(user);
    }

    public void deleteUser(DeleteUserRequest request) {
        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException(request.getUserId() + "를 찾을 수 없음."));

        if(user.getUserAccount().getEmail() != request.getEmail()) {
            new RuntimeException(request.getEmail() + "와" + request.getEmail() + "가 일치하지 않음.");
        } else if(user.getUserAccount().getPassword() != request.getPassword()){
            new RuntimeException(request.getPassword() + "와" + request.getPassword() + "가 일치하지 않음.");
        } else {
            userSettingService.deleteUserSetting(user.getUserSetting().getUserSettingId());
            userAccountService.deleteUserAccount(user.getUserAccount().getUserAccountId());
            userRepository.deleteById(user.getUserId());
        }
    }
}
