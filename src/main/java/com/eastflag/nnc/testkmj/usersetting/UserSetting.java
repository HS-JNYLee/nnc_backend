package com.eastflag.nnc.testkmj.usersetting;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_setting")
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userSettingId;

    @Column(nullable = false)
    private Boolean voiceGuide = true;

    @Column(nullable = false)
    private Boolean alertRoute = true;

    @Column(nullable = false)
    private Boolean alertDanger = true;

    @Column(nullable = false)
    private Boolean lowBus = false;

    @Column(nullable = false)
    private Boolean elevatorFirst = false;
}
