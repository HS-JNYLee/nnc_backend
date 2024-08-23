package com.eastflag.nnc.user1.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 변경할 유저의 정보
 *
 * userId (변경 대상 ID): user1.userId
 * name (이름): user1.name
 * telNum (전화번호): user1.tel_num
 * email (이메일): user_account.email
 * password (비밀번호): user_account.password
 * passwordSalt (솔트된 비밀번호): user_account.password_salt
 * hashAlgorithmId (해싱 알고리즘): user_account.hash_algorithm_id
 * updateAt (변경 요청 시간): user_account.updated_at
 * address (주소): user_account.address
 * detailAddress (세부주소): user_account.detail_address
 */
@Getter
@Setter
@Builder
public class UpdateUserRequest {
    private int userId;
    private String name;
    private String telNum;
    private String email;
    private String password;
    private String passwordSalt;
    private String hashAlgorithmId;
    private Timestamp updatedAt;
    private String address;
    private String detailAddress;
}