package com.allinpay.tdd.marsrover.entity;

public class TurnCommand extends CommandType {
    @Override
    public void parse(MarsRover rover, String key, String value) {
        rover.getCommandMap().put(key, value);
    }

    @Override
    public void execute(LandPoint landPoint, String key, String value) {
        landPoint.orient(value.split(",")[0]);
    }
}
