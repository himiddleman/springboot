package com.allinpay.marsrover;

import lombok.Data;

@Data
public class LandPoint {
    private Integer xAxis;
    private Integer yAxis;
    private String orientation;

    public LandPoint(int xAxis, int yAxis, String orientation) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.orientation = orientation;
    }

    public void forward(int distance) {
        switch (orientation) {
            case "N":
                this.yAxis = yAxis + distance;
                break;
            case "S":
                this.yAxis = yAxis - distance;
                break;
            case "W":
                this.xAxis = xAxis - distance;
                break;
            case "E":
                this.xAxis = xAxis + distance;
                break;
            default:
                //do nothing
        }
    }

    public void back(int distance) {
        if ("N".equals(orientation)) {
            this.yAxis = yAxis - distance;
        }
        if ("S".equals(orientation)) {
            this.yAxis = yAxis + distance;
        }
        if ("W".equals(orientation)) {
            this.xAxis = xAxis + distance;
        }
        if ("E".equals(orientation)) {
            this.xAxis = xAxis - distance;
        }
    }

    public void orient(String current) {
        if ("N".equals(orientation)) {
            if ("l".equals(current)) {
                this.orientation = "W";
            } else {
                this.orientation = "E";
            }
        }
        if ("S".equals(orientation)) {
            if ("l".equals(current)) {
                this.orientation = "E";
            } else {
                this.orientation = "W";
            }
        }

        if ("E".equals(orientation)) {
            if ("l".equals(current)) {
                this.orientation = "N";
            } else {
                this.orientation = "S";
            }
        }

        if ("W".equals(orientation)) {
            if ("l".equals(current)) {
                this.orientation = "S";
            } else {
                this.orientation = "N";
            }
        }
    }
}
