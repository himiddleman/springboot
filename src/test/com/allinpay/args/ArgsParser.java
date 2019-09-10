package com.allinpay.args;

public class ArgsParser {
    private final ArgsSchema schema;
    private final ArgsCommand command;

    public ArgsParser(String schemaConfig, String commandLine) {
        this.schema = new ArgsSchema(schemaConfig);
        this.command = new ArgsCommand(commandLine);
    }

    public Object getValue(String flag) {
        return schema.getValue(flag, command.getValue(flag));
    }
}
