package com.eastflag.nnc.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 삭제할 유저의 정보
 *
 * email (이메일): user_account.email
 * password (비밀번호): user_account.password
 */
@Getter
@Setter
@Builder
public class DeleteUserRequest {
    private int userId;
    private String email;
    private String password;
}