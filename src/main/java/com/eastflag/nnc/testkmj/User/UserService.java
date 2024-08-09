package com.eastflag.nnc.testkmj.User;

import com.eastflag.nnc.testkmj.UserAccount.UserAccountService;
import com.eastflag.nnc.testkmj.UserSetting.UserSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    // 해싱을 위한 것이라고 추정
    //private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final UserAccountService userAccountService; // 미완
    private final UserSettingService userSettingService; // 미완

    public void createUser(CreateUserRequest request) {
        RoleId roleId;
        if(request.getCaregiverId() == null) roleId = RoleId.CAREGIVER; else roleId = RoleId.CARETAKER;
        var user = User.builder()
                .name(request.getName())
                .telNum(request.getTelNum())
                .roleId(roleId)
                .build();
        userRepository.save(user);

        userAccountService.createUserAccount(user,request);
        userSettingService.createUserSetting(user);
    }
}
