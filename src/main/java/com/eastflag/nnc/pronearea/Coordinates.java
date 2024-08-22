package com.eastflag.nnc.pronearea;

import java.util.List;

class Coordinates {
    private List<Coordinate> coordinates;

    // Constructor
    public Coordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    // Getters and Setters
    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "coordinates=" + coordinates +
                '}';
    }
}