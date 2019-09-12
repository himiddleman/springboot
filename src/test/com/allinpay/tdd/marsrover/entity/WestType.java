package com.allinpay.tdd.marsrover.entity;

public class WestType extends OrientType {
    @Override
    public void forward(LandPoint landPoint, int distance) {
        landPoint.setXAxis(landPoint.getXAxis() - distance);
    }

    @Override
    public void back(LandPoint landPoint, int distance) {
        landPoint.setXAxis(landPoint.getXAxis() + distance);
    }

    @Override
    public void orient(LandPoint landPoint, String current) {
        if ("l".equals(current)) {
            landPoint.setOrientType(OrientType.getInstance("S"));
        } else {
            landPoint.setOrientType(OrientType.getInstance("N"));
        }
    }
}
