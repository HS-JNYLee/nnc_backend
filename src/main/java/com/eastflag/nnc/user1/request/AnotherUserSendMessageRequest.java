package com.eastflag.nnc.user1.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnotherUserSendMessageRequest {
    private int userId;
    private String topic;
    private String title;
    private String body;
}
