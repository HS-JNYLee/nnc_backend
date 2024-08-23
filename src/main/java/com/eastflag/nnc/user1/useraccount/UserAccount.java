package com.eastflag.nnc.user1.useraccount;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

/**
 * UserAccount Entity: user_account Table 정보
 *
 * userAccountId (고유ID): 유저 계정(Account) 식별자
 * email (이메일): 이메일
 * password (비밀번호): 비밀번호 
 * passwordSalt (솔트된 비밀번호): 솔트된 비밀번호
 * hashAlgorithmId (해싱 알고리즘): 해싱 알고리즘
 * createdAt (생성 시간): Entity가 생성된 시간
 * updateAt (변경 시간): Entity가 최근 변경된 시간
 * address (주소): 유저 주소
 * detailAddress (상세 주소): 유저 상세 주소
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userAccountId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String passwordSalt;

    @Column(nullable = false)
    private String hashAlgorithmId;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Column(nullable = false)
    private String address;

    private String detailAddress;
}
