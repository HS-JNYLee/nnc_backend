package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.testkmj.useraccount.UserAccountService;
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
    public void createUser(CreateUserRequest request) {
        RoleId roleId;
        if(request.getCaregiverId() == null) roleId = RoleId.CAREGIVER; else roleId = RoleId.CARETAKER;
        var user = User.builder()
                .name(request.getName())
                .telNum(request.getTelNum())
                .roleId(roleId)
                .build();
        userRepository.save(user);

        // request에 남아있는 정보들 각 userAccount, userSetting으로 분기.
        userAccountService.createUserAccount(user,request);
        userSettingService.createUserSetting(user);
    }
}
