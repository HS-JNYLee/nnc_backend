package com.eastflag.nnc.testkmj.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

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
