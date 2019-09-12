package com.allinpay.tdd.marsrover.entity;


public abstract class OrientType {
    public static OrientType getInstance(String orientation) {
        switch (orientation) {
            case "N":
                return new NorthType();
            case "S":
                return new SouthType();
            case "E":
                return new EastType();
            case "W":
                return new WestType();
            default:
                throw new IllegalArgumentException();
        }
    }

    public abstract void forward(LandPoint landPoint, int distance);

    public abstract void back(LandPoint landPoint, int distance);

    public abstract void orient(LandPoint landPoint, String current);
}
