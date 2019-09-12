package com.allinpay.tdd.args;

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

    @Test
    public void test_extension_string_list_and_int_list() {
        ArgsCommand command = new ArgsCommand("-l true -p -9 -d /usr/local -g this,is,a,list -f 1,-2,-3,4");
        assertThat(command.getValue("g")).isEqualTo("this,is,a,list");
        assertThat(command.getValue("f")).isEqualTo("1,-2,-3,4");
    }
}
