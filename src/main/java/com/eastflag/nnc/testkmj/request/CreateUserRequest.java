package com.eastflag.nnc.testkmj.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 생성할 유저(계정)의 정보
 *
 * email (이메일): user_account.email
 * password (비밀번호): user_account.password
 * name (이름): user1.name
 * telNum (전화번호): user1.tel_num
 * address (주소): user_account.address
 * detailAddress (상세 주소): user_account.detail_address
 * caregiverId (보호자 인증하기 후 반환된 Id 값): user_relation.caregiver_id ※ -1일 경우 보호자 계정
 * caregiverRelation (사용자와의 관계): user_relation.relation ※ -1이 아닐 경우에만 참조
 * createdAt (생성 시간): user_account.created_at
 * updatedAt (변경 시간) ※ 생성시간과 동일: user_account.updated_at
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
    private String fcmToken;
    private int caregiverId;
    private String caregiverRelation;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}