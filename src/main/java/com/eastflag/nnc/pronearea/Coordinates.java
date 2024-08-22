package com.eastflag.nnc.pronearea;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
class Coordinates implements Serializable {
    private List<Coordinate> coordinates;

    public Coordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "coordinates=" + coordinates +
                '}';
    }
}