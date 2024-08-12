package com.eastflag.nnc.testkmj.fcm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FcmRequest1 {
    private int userId;
    private String fcmToken;
}
