package com.eastflag.nnc.fcm.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FcmRequest {
    private int userId;
    private String fcmToken;
}
