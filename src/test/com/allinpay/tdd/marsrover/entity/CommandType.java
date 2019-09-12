package com.allinpay.tdd.marsrover.entity;

public abstract class CommandType {
    public static CommandType getInstance(String command) {
        switch (command) {
            case "area":
                return new AreaCommand();
            case "init":
                return new InitCommand();
            case "move":
                return new MoveCommand();
            case "turn":
                return new TurnCommand();
            default:
                throw new IllegalArgumentException();
        }
    }

    public abstract void parse(MarsRover rover, String key, String value);

    public void execute(LandPoint landPoint, String key, String value) {
        throw new IllegalArgumentException();
    }
}
