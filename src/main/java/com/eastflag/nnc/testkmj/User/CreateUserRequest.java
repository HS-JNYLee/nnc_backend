package com.eastflag.nnc.testkmj.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

/**
 * email (이메일): user_account.email
 * password (비밀번호): user_account.password
 * name (이름): user.name
 * telNum (전화번호): user.tel_num
 * address (주소): user_account.address
 * detailAddress (상세 주소): user_account.detail_address
 * caregiverId (보호자 인증하기 후 반환된 Id 값): relation.caregiver_id
 * createdAt (생성시간): user_account.created_at
 * updatedAt (업데이트 시간) ※ 생성시간과 동일: user_account.updated_at
 */
@Getter
@Setter
@Builder
public class CreateUserRequest {
    private String email;
    private String password;
    private String name;
    private String telNum;
    private String address;
    private String detailAddress;
    private String caregiverId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
