package com.eastflag.nnc.fcm;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
// https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages?hl=ko
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AndroidNotification {
    private String title;
    private String body;
    private String icon;
    private String color;
    private String sound;
    private String tag;
    private String clickAction;
    private String bodyLocKey;
    private List<String> bodyLocArgs;
    private String titleLocKey;
    private List<String> titleLocArgs;
    private String channelId;
    private String ticker;
    private boolean sticky;
    private String eventTime;
    private boolean localOnly;
    private NotificationPriority notificationPriority;
    private boolean defaultSound;
    private boolean defaultVibrateTimings;
    private boolean defaultLightSettings;
    private List<String> vibrateTimings;
    private Visibility visibility;
    private int notificationCount;
    private LightSettings lightSettings;
    private String image;
    private boolean bypassProxyNotification;
    private Proxy proxy;

    public enum NotificationPriority {
        PRIORITY_UNSPECIFIED,
        PRIORITY_MIN,
        PRIORITY_LOW,
        PRIORITY_DEFAULT,
        PRIORITY_HIGH,
        PRIORITY_MAX
    }

    public enum Visibility {
        VISIBILITY_UNSPECIFIED,
        PRIVATE,
        PUBLIC,
        SECRET
    }

    public enum Proxy {
        PROXY_UNSPECIFIED,
        ALLOW,
        DENY,
        IF_PRIORITY_LOWERED
    }

    @Data
    public static class LightSettings {
        private Object color;
        private String lightOnDuration;
        private String lightOffDuration;
    }
}
