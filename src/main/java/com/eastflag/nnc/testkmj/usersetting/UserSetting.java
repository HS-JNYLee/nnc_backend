package com.eastflag.nnc.testkmj.usersetting;

import jakarta.persistence.*;
import lombok.*;

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