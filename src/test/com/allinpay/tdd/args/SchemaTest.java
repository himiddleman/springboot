package com.allinpay.tdd.args;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SchemaTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_bool() {
        ArgsSchema schema = new ArgsSchema("l:bool");
        assertThat(schema.getValue("l", "true")).isEqualTo(Boolean.TRUE);
        assertThat(schema.getValue("l", "false")).isEqualTo(Boolean.FALSE);
        assertThat(schema.getValue("l", "")).isEqualTo(Boolean.FALSE);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("布尔类型的参数必须是true或者false");
        schema.getValue("l", "abc");
    }

    @Test
    public void test_string() {
        ArgsSchema schema = new ArgsSchema("l:bool,p:int,d:string");
        assertThat(schema.getValue("d", "/usr/local")).isEqualTo("/usr/local");
        assertThat(schema.getValue("d", "")).isEqualTo("");
    }

    @Test
    public void test_int() {
        ArgsSchema schema = new ArgsSchema("l:bool,p:int");
        assertThat(schema.getValue("p", "8080")).isEqualTo(8080);
        assertThat(schema.getValue("p", "")).isEqualTo(0);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("转换为整型数异常");
        assertThat(schema.getValue("p", "abc"));
    }

    @Test
    public void test_string_list() {
        ArgsSchema schema = new ArgsSchema("l:bool,p:int,d:string,g:slist");
        List list = new ArrayList<>();
        list.add("this");
        list.add("is");
        list.add("a");
        list.add("list");
        assertThat(schema.getValue("g", "this,is,a,list")).isEqualTo(list);
        assertThat(schema.getValue("g", "")).isEqualTo(new ArrayList<>());
    }

    @Test
    public void test_int_list() {
        ArgsSchema schema = new ArgsSchema("l:bool,p:int,d:string,g:slist,f:ilist");
        List list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        list.add(-3);
        list.add(5);
        assertThat(schema.getValue("f", "1,-2,-3,5")).isEqualTo(list);
        assertThat(schema.getValue("f", "")).isEqualTo(new ArrayList<>());
    }
}
