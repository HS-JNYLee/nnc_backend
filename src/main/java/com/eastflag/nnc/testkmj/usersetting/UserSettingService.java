package com.eastflag.nnc.testkmj.usersetting;

import com.eastflag.nnc.testkmj.request.UpdateUserSettingRequest;
import com.eastflag.nnc.testkmj.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingService {
    private final UserService userService;
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

    public UserSetting updateUserSetting(UpdateUserSettingRequest request) {
        var userSettingId = userService.getUserSettingId(request.getUserId());

        var beforeSetting = userSettingRepository
                .findById(userSettingId)
                .orElseThrow(() -> new RuntimeException(userSettingId + "를 찾을 수 없음."));

        var userSetting = UserSetting.builder()
                .userSettingId(userSettingId)
                .voiceGuide(request.getVoiceGuide() != null ? request.getVoiceGuide() : beforeSetting.getVoiceGuide())
                .alertRoute(request.getAlertRoute() != null ? request.getAlertRoute() : beforeSetting.getAlertRoute())
                .alertDanger(request.getAlertDanger() != null ? request.getAlertDanger() : beforeSetting.getAlertDanger())
                .lowBus(request.getLowBus() != null ? request.getLowBus() : beforeSetting.getLowBus())
                .elevatorFirst(request.getElevatorFirst() != null ? request.getLowBus() : beforeSetting.getLowBus())
                .build();
        userSettingRepository.save(userSetting);

        return userSetting;
    }
}