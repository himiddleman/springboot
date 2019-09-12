package com.allinpay.tdd.marsrover.entity;

import lombok.Data;

@Data
public class MarsArea {
    private Integer xAxis;
    private Integer yAxis;

    public MarsArea(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }
}
