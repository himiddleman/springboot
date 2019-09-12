package com.allinpay.tdd.marsrover.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
public class MarsRover {
    private MarsArea marsArea;
    private LandPoint landPoint;
    private Map<String, String> commandMap = new HashMap<>();

    //area:100,100 init:0,0,E move:f,20 turn:l
    public MarsRover(String commandLine) {
        Arrays.asList(commandLine.split("\\s+")).stream().forEach(command -> {
            parseCommandLine(command);
        });
    }

    public LandPoint getCurrentPosition() {
        for (Map.Entry<String, String> entry : commandMap.entrySet()) {
            executeCommand(entry);
        }
        return landPoint;
    }

    private void parseCommandLine(String command) {
        CommandType.getInstance(command.split(":")[0]).parse(this,
                command.split(":")[0], command.split(":")[1]);
    }

    private void executeCommand(Map.Entry<String, String> entry) {
        CommandType.getInstance(entry.getKey()).execute(landPoint, entry.getKey(), entry.getValue());
    }
}
