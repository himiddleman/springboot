package com.allinpay.tdd.marsrover.entity;

public class InitCommand extends CommandType {
    @Override
    public void parse(MarsRover rover, String key, String value) {
        rover.setLandPoint(new LandPoint(Integer.parseInt(value.split(",")[0]), Integer.parseInt(value.split(",")[1]),
                value.split(",")[2]));
    }
}
