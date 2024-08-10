package com.eastflag.nnc.testkmj.usersetting;

import com.eastflag.nnc.testkmj.request.UpdateUserSettingRequest;
import com.eastflag.nnc.testkmj.user.UserService1;
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

    public UserSetting updateUserSetting(int userSettingId, UpdateUserSettingRequest request) {
//        var beforeSetting = userSettingRepository
//                .findById(userSettingId)
//                .orElseThrow(() -> new RuntimeException(userSettingId + "를 찾을 수 없음."));
//
//        var userSetting = UserSetting.builder()
//                .userSettingId(userSettingId)
//                .voiceGuide(request.getVoiceGuide() != null ? request.getVoiceGuide() : beforeSetting.getVoiceGuide())
//                .alertRoute(request.getAlertRoute() != null ? request.getAlertRoute() : beforeSetting.getAlertRoute())
//                .alertDanger(request.getAlertDanger() != null ? request.getAlertDanger() : beforeSetting.getAlertDanger())
//                .lowBus(request.getLowBus() != null ? request.getLowBus() : beforeSetting.getLowBus())
//                .elevatorFirst(request.getElevatorFirst() != null ? request.getLowBus() : beforeSetting.getLowBus())
//                .build();

        var userSetting = userSettingRepository
                .findById(userSettingId)
                .orElseThrow(() -> new RuntimeException(userSettingId + "를 찾을 수 없음."));
        if(request.getVoiceGuide() != null) userSetting.setVoiceGuide(request.getVoiceGuide());
        if(request.getAlertRoute() != null) userSetting.setAlertRoute(request.getAlertRoute());
        if(request.getAlertDanger() != null) userSetting.setAlertDanger(request.getAlertDanger());
        if(request.getLowBus() != null) userSetting.setLowBus(request.getLowBus());
        if(request.getElevatorFirst() != null) userSetting.setElevatorFirst(request.getElevatorFirst());

        userSettingRepository.save(userSetting);

        return userSetting;
    }

    public UserSetting getUserSetting(int userSettingId) {
        var userSetting = userSettingRepository
                .findById(userSettingId)
                .orElseThrow(() -> new RuntimeException(userSettingId + "를 찾을 수 없음."));
        return userSetting;
    }
}