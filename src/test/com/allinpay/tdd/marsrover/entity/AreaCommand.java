package com.allinpay.tdd.marsrover.entity;

public class AreaCommand extends CommandType {
    @Override
    public void parse(MarsRover rover, String key, String value) {
        rover.setMarsArea(new MarsArea(Integer.parseInt(value.split(",")[0]),
                Integer.parseInt(value.split(",")[1])));
    }
}
