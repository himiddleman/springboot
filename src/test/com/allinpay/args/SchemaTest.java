package com.allinpay.args;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
}
