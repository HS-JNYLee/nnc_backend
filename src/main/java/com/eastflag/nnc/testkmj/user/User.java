package com.eastflag.nnc.testkmj.user;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;

    @Column(unique = true, nullable = false)
    private String telNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleId roleId;
}
