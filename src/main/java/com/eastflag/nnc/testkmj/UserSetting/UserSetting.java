package com.eastflag.nnc.testkmj.UserSetting;

import java.util.UUID;

public class UserSetting {
    private final UUID userId;
    private final Boolean voiceGuide;
    private final Boolean alertRoute;
    private final Boolean alertDanger;
    private final Boolean lowBus;
    private final Boolean elevatorFirst;

    public UserSetting(UUID userId, Boolean voiceGuide, Boolean alertRoute, Boolean alertDanger, Boolean lowBus, Boolean elevatorFirst) {
        this.userId = userId;
        this.voiceGuide = voiceGuide;
        this.alertRoute = alertRoute;
        this.alertDanger = alertDanger;
        this.lowBus = lowBus;
        this.elevatorFirst = elevatorFirst;
    }

    public UUID getUserId() {
        return userId;
    }
    public Boolean getVoiceGuide() {
        return voiceGuide;
    }
    public Boolean getAlertRoute() {
        return alertRoute;
    }
    public Boolean getAlertDanger() {
        return alertDanger;
    }
    public Boolean getLowBus() {
        return lowBus;
    }
    public Boolean getElevatorFirst() {
        return elevatorFirst;
    }
}
