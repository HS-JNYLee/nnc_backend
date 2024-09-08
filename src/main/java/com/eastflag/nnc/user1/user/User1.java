package com.eastflag.nnc.user1.user;

import com.eastflag.nnc.auth.CustomUserDetails;
import com.eastflag.nnc.user1.useraccount.UserAccount;
import com.eastflag.nnc.user1.usersetting.UserSetting;
import jakarta.persistence.*;
import lombok.*;

/**
 * User1 Entity: user1 Table 정보
 *
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
@Getter
@Setter
@Table(name = "user1")
public class User1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;

    @Column(unique = true, nullable = false)
    private String telNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role1 role1;

    @OneToOne
    @JoinColumn(name = "user_setting_id")
    private UserSetting userSetting;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    public CustomUserDetails getPrincipalDetails() {
        return new CustomUserDetails(this);
    }
}
