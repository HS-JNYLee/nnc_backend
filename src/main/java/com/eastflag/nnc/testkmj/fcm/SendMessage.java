package com.eastflag.nnc.testkmj.fcm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SendMessage {
    private int userId;
    private String topic;
    private String title;
    private String body;
}
