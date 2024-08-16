package com.eastflag.nnc.testkmj.usersetting;

import com.eastflag.nnc.testkmj.error.User1Exception;
import com.eastflag.nnc.testkmj.request.UpdateUserSettingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.eastflag.nnc.testkmj.error.ErrorCode.USER_ACCOUNT_EMAIL_NOT_FOUND;
import static com.eastflag.nnc.testkmj.error.ErrorCode.USER_SETTING_ID_NOT_FOUND;

/**
 * 유저 설정 관리 Service 클래스
 *
 */
@Service
@RequiredArgsConstructor
public class UserSettingService {
    private final UserSettingRepository userSettingRepository;

    /**
     * 유저 설정 Entity를 DataBase에 생성하는 함수
     *
     * @return 생성된 유저 설정 Entity
     */
    public UserSetting createUserSetting() {
        var userSetting = UserSetting.builder()
                .voiceGuide(true)
                .alertRoute(true)
                .alertDanger(true)
                .alertDanger(false)
                .lowBus(false)
                .elevatorFirst(false)
                .build();
        userSettingRepository.save(userSetting);

        return userSetting;
    }

    /**
     * 유저 설정 Entity를 DataBase에 삭제하는 함수
     *
     * @param userSettingId 삭제할 Entity ID
     */
    public void deleteUserSetting(int userSettingId) {
        userSettingRepository.deleteById(userSettingId);
    }

    /**
     * 특정 Entity 데이터를 변경하는 함수
     *
     * @param userSettingId 변경할 Entity ID
     * @param request UserController1.updateUserSetting에서 가져온 정보
     * @return 변경된 UserSetting Entity
     */
    public UserSetting updateUserSetting(int userSettingId, UpdateUserSettingRequest request) {
        var userSetting = userSettingRepository
                .findById(userSettingId)
                .orElseThrow(() -> new User1Exception(USER_SETTING_ID_NOT_FOUND));

        // null이 아닌 값만 setter로 수정한다.
        if(request.getVoiceGuide() != null) userSetting.setVoiceGuide(request.getVoiceGuide());
        if(request.getAlertRoute() != null) userSetting.setAlertRoute(request.getAlertRoute());
        if(request.getAlertDanger() != null) userSetting.setAlertDanger(request.getAlertDanger());
        if(request.getLowBus() != null) userSetting.setLowBus(request.getLowBus());
        if(request.getElevatorFirst() != null) userSetting.setElevatorFirst(request.getElevatorFirst());

        userSettingRepository.save(userSetting);

        return userSetting;
    }

    /**
     * 특정 Entity 데이터를 반환하는 함수
     *
     * @param userSettingId 반환받을 UserSetting의 user_setting_id
     * @return user_setting_id에 맞는 UserSetting Entity
     */
    public UserSetting getUserSetting(int userSettingId) {
        var userSetting = userSettingRepository
                .findById(userSettingId)
                .orElseThrow(() -> new User1Exception(USER_SETTING_ID_NOT_FOUND));
        return userSetting;
    }
}