package com.eastflag.nnc.testkmj.UserSetting;

import com.eastflag.nnc.testkmj.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingService {
    private final UserSettingRepository userSettingRepository;

    public void createUserSetting(User user) {
        var userSetting = UserSetting.builder()
                .user(user)
                .build();
        userSettingRepository.save(userSetting);
    }
}