package com.allinpay.tdd.marsrover.entity;

public class SouthType extends OrientType {
    @Override
    public void forward(LandPoint landPoint, int distance) {
        landPoint.setYAxis(landPoint.getYAxis() - distance);
    }

    @Override
    public void back(LandPoint landPoint, int distance) {
        landPoint.setYAxis(landPoint.getYAxis() + distance);
    }

    @Override
    public void orient(LandPoint landPoint, String current) {
        if ("l".equals(current)) {
            landPoint.setOrientType(OrientType.getInstance("E"));
        } else {
            landPoint.setOrientType(OrientType.getInstance("W"));
        }
    }
}
