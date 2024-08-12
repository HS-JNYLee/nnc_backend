package com.eastflag.nnc.fcm;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Notification {
    private String title;
    private String body;
}
