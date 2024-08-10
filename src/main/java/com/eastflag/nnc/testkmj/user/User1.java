package com.eastflag.nnc.testkmj.user;

import com.eastflag.nnc.testkmj.useraccount.UserAccount;
import com.eastflag.nnc.testkmj.usersetting.UserSetting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * userId (고유ID): 유저 식별자
 * name (이름): 사용자 성명
 * telNum (전화번호): 사용자 연락처
 * roleId (역할): 사용자 역할 및 권한
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;

    @Column(unique = true, nullable = false)
    private String telNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne
    @JoinColumn(name = "user_setting_id")
    private UserSetting userSetting;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
}
