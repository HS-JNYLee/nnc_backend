package com.eastflag.nnc.user1.usersetting;

import jakarta.persistence.*;
import lombok.*;

/**
 * UserSetting Entity: user_setting Table 정보
 *
 * userSettingId (고유ID): 유저 설정(Setting) 식별자
 * voiceGuide (음성 길 안내): 음성 안내 토글
 * alertRotue (경로 이탈 안내): 경로 이탈 안내 토글
 * alertDanger (위험 지역 알림): 위험 지역 알림 토글
 * lowBus (저상버스 우선): 저상버스 우선 안내
 * elevatorFirst (엘레베이터 우선): 지하철 엘레베이터 우선 안내
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user_setting")
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userSettingId;

    @Column(nullable = false)
    private Boolean voiceGuide;

    @Column(nullable = false)
    private Boolean alertRoute;

    @Column(nullable = false)
    private Boolean alertDanger;

    @Column(nullable = false)
    private Boolean lowBus;

    @Column(nullable = false)
    private Boolean elevatorFirst;
}