package com.eastflag.nnc.testkmj.request;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUserSettingRequest {
    private int userId;
    private Boolean voiceGuide;
    private Boolean alertRoute;
    private Boolean alertDanger;
    private Boolean lowBus;
    private Boolean elevatorFirst;
}
