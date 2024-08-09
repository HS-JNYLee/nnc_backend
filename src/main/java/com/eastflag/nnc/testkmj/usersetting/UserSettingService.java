package com.eastflag.nnc.testkmj.usersetting;

import com.eastflag.nnc.testkmj.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingService {
    private final UserSettingRepository userSettingRepository;

    public UserSetting createUserSetting() {
        var userSetting = UserSetting.builder()
                .build();
        userSettingRepository.save(userSetting);

        return userSetting;
    }


    public void deleteUserSetting(int userSettingId) {
        userSettingRepository.deleteById(userSettingId);
    }
}