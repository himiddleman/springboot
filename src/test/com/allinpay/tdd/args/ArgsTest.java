package com.allinpay.tdd.args;

import org.junit.Test;

import java.util.List;

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

    @Test
    public void test_extension_list_type() {
        ArgsParser argsParser = new ArgsParser("l:bool,p:int,d:string,g:slist,f:ilist",
                "-l -p -d -g this,is,a,list -f 1,-2,-3");
        assertThat((List) argsParser.getValue("g")).hasSize(4).contains("this", "is", "a", "list");
        assertThat((List) argsParser.getValue("f")).hasSize(3).contains(1, -2, -3).doesNotContain(4);
    }
}
