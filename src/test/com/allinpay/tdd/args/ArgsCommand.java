package com.allinpay.tdd.args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class ArgsCommand {
    private Map<String, String> commandMap;

    public ArgsCommand(String commandInput) {
        commandMap = new HashMap<>();
        ListIterator<String> iterator = Arrays.asList(commandInput.split("\\s+")).listIterator();
        //-l -p -9 -d
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (iterator.hasNext()) {
                String value = iterator.next();
                if (isValue(value)) {
                    commandMap.put(key.substring(1), value);
                } else {
                    commandMap.put(key.substring(1), "");
                    iterator.previous();
                }
            } else {
                commandMap.put(key.substring(1), "");
            }
        }
    }

    private boolean isValue(String value) {
        if (value.charAt(0) != '-') {
            return true;
        }
        if (value.length() > 2) {
            return true;
        }
        if (value.charAt(1) >= '0' && value.charAt(1) <= '9') {
            return true;
        }
        return false;
    }

    public String getValue(String flag) {
        return commandMap.get(flag);
    }
}
