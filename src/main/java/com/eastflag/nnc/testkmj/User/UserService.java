package com.eastflag.nnc.testkmj.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    // 해싱을 위한 것이라고 추정
    //private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    //private final UserAccountService userAccountService; // 미완
    //private final UserSettingService userSettingService; // 미완

    public void createUser(CreateUserRequest request) {
        // user_account에 관한 속성
        var email = request.getEmail();
        var password = request.getPassword();
        var address = request.getAddress();
        var detailAddress = request.getDetailAddress();
        var createdAt = request.getCreatedAt();
        var updatedAt = request.getUpdatedAt();

        RoleId roleId;
        if(request.getCaregiverId() == null) roleId = RoleId.CAREGIVER; else roleId = RoleId.CARETAKER;
        var user = User.builder()
                .name(request.getName())
                .telNum(request.getTelNum())
                .roleId(roleId)
                .build();

        userAccountService.createUserAccount(user.getUserId(),request);
        userSettingService.createUserSetting(user.getUserId(),request);
    }
}
