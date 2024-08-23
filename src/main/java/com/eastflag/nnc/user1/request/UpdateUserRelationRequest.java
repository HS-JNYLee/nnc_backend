package com.eastflag.nnc.user1.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUserRelationRequest {
    private int caretakerId;
    private int caregiverId;
    private String relation;
}
