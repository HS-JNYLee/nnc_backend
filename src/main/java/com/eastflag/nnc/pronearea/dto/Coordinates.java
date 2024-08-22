package com.eastflag.nnc.pronearea.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
@Builder
public class Coordinates{
    private List<Coordinate> coordinates;
}