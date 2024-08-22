package com.eastflag.nnc.pronearea.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class Coordinate {
    private final Double latitude;
    private final Double longitude;
}
