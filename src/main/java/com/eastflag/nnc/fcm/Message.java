package com.eastflag.nnc.fcm;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Message{
    private String name;
    private String data;
    private Notification notification;
    private String token;
    private String topic;
    private String condition;
    private FcmOptions fcmOptions;

}
