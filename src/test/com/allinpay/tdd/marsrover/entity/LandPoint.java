package com.allinpay.tdd.marsrover.entity;

import lombok.Data;

@Data
public class LandPoint {
    private Integer xAxis;
    private Integer yAxis;
    private OrientType orientType;

    public LandPoint(int xAxis, int yAxis, String orientation) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        orientType = OrientType.getInstance(orientation);
    }

    public void forward(int distance) {
        orientType.forward(this, distance);
    }

    public void back(int distance) {
        orientType.back(this, distance);
    }

    public void orient(String current) {
        orientType.orient(this, current);
    }
}
