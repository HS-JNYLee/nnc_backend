package com.eastflag.nnc.fcm.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class NavigationRequest {
    private MessageWrapper messageWrapper;
    private List<Double> passedRoute;
}
