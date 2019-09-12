package com.allinpay.marsrover;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MarsRover {
    private MarsArea marsArea;
    private LandPoint landPoint;
    private Map<String, String> commandMap = new HashMap<>();

    //area:100,100 init:0,0,E move:f,20 turn:l
    public MarsRover(String commandLine) {
        Arrays.asList(commandLine.split("\\s+")).stream().forEach(command -> {
            String key = command.split(":")[0];
            String value = command.split(":")[1];
            parseCommandLine(key, value);
        });
    }

    private void parseCommandLine(String key, String value) {
        if ("area".equals(key)) {
            marsArea = new MarsArea(Integer.parseInt(value.split(",")[0]),
                    Integer.parseInt(value.split(",")[1]));
        } else {
            marsArea = new MarsArea(100, 100);
        }
        if ("init".equals(key)) {
            landPoint = new LandPoint(Integer.parseInt(value.split(",")[0]), Integer.parseInt(value.split(",")[1]),
                    value.split(",")[2]);
        }
        if ("move".equals(key) || "turn".equals(key)) {
            commandMap.put(key, value);
        }
    }

    //area:100,100 init:0,0,E move:f,20 turn:l
    public LandPoint getCurrentPosition() {
        for (Map.Entry<String, String> entry : commandMap.entrySet()) {
            executeCommand(entry);
        }
        return landPoint;
    }

    private void executeCommand(Map.Entry<String, String> entry) {
        String key = entry.getKey();
        String value = entry.getValue();
        if ("move".equals(key)) {
            if ("f".equals(value.split(",")[0])) {
                landPoint.forward(Integer.parseInt(value.split(",")[1]));
            } else if ("b".equals(value.split(",")[0])) {
                landPoint.back(Integer.parseInt(value.split(",")[1]));
            }
        }
        if ("turn".equals(key)) {
            landPoint.orient(value.split(",")[0]);
        }
    }
}
