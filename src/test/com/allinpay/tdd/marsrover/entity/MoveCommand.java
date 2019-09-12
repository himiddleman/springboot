package com.allinpay.tdd.marsrover.entity;

public class MoveCommand extends CommandType {
    @Override
    public void parse(MarsRover rover, String key, String value) {
        rover.getCommandMap().put(key, value);
    }

    @Override
    public void execute(LandPoint landPoint, String key, String value) {
        if ("f".equals(value.split(",")[0])) {
            landPoint.forward(Integer.parseInt(value.split(",")[1]));
        } else if ("b".equals(value.split(",")[0])) {
            landPoint.back(Integer.parseInt(value.split(",")[1]));
        }
    }
}
