package com.eastflag.nnc.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 변경할 유저 설정 정보
 *
 * userId (변경 대상 ID): user1.userId
 * voiceGuide (음성 안내): user_setting.voice_guide
 * alertRoute (경로 이탈 안내): user_setting.alert_route
 * alertDanger (위험 지역 안내): user_setting.alert_danger
 * lowBus (저상버스 우선 안내): user_setting.low_bus
 * elevatorFirst (엘레베이터 우선 안내): user_setting.elevator_first
 */
@Getter
@Setter
@Builder
public class UpdateUserSettingRequest {
    private int userId;
    private Boolean voiceGuide;
    private Boolean alertRoute;
    private Boolean alertDanger;
    private Boolean lowBus;
    private Boolean elevatorFirst;
}