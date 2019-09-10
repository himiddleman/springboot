package com.allinpay.args;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArgsTest {
    @Test
    public void test_normal_parameter() {
        ArgsParser argsParser = new ArgsParser("l:bool,p:int,d:string", "-l true -p 8080 -d /usr/local");
        assertThat(argsParser.getValue("l")).isEqualTo(Boolean.TRUE);
        assertThat(argsParser.getValue("p")).isEqualTo(8080);
        assertThat(argsParser.getValue("d")).isEqualTo("/usr/local");
    }

    @Test
    public void test_abnormal_parameter() {
        ArgsParser argsParser = new ArgsParser("l:bool,p:int,d:string", "-l -p -9 -d ");
        assertThat(argsParser.getValue("p")).isEqualTo(-9);
    }

    @Test
    public void test_missing_value() {
        ArgsParser argsParser = new ArgsParser("l:bool,p:int,d:string", "-l -p -d ");
        assertThat(argsParser.getValue("l")).isEqualTo(Boolean.FALSE);
        assertThat(argsParser.getValue("p")).isEqualTo(0);
        assertThat(argsParser.getValue("d")).isEqualTo("");
    }
}
