package com.allinpay.args;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CommandTest {
    @Test
    public void test_value_missing() {
        ArgsCommand command = new ArgsCommand("-l -p -d");
        assertThat(command.getValue("l")).isEqualTo("");
        assertThat(command.getValue("p")).isEqualTo("");
        assertThat(command.getValue("d")).isEqualTo("");
    }

    @Test
    public void test_name_value() {
        ArgsCommand command = new ArgsCommand("-l true -p 8080 -d /usr/local");
        assertThat(command.getValue("l")).isEqualTo("true");
        assertThat(command.getValue("p")).isEqualTo("8080");
        assertThat(command.getValue("d")).isEqualTo("/usr/local");
    }

    @Test
    public void test_negative_value() {
        ArgsCommand command = new ArgsCommand("-l true -p -9 -d /usr/local");
        assertThat(command.getValue("p")).isEqualTo("-9");
    }
}
