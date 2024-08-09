package com.eastflag.nnc.testkmj.UserSetting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingService {
    private final UserSettingRepository userSettingRepository;

    public void createUserSetting(int userId) {
        var userSetting = UserSetting.builder()
                .userId(userId)
                .build();
        userSettingRepository.save(userSetting);
    }
}