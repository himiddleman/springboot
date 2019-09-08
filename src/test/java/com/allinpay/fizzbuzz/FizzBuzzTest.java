package com.allinpay.fizzbuzz;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_normal() {
        assertThat(new FizzBuzz().of(1)).isEqualTo("1");
        assertThat(new FizzBuzz().of(3)).isEqualTo("Fizz");
        assertThat(new FizzBuzz().of(5)).isEqualTo("Buzz");
        assertThat(new FizzBuzz().of(15)).isEqualTo("FizzBuzz");
        assertThat(new FizzBuzz().of(13)).isEqualTo("Fizz");
        assertThat(new FizzBuzz().of(52)).isEqualTo("Buzz");
    }

    @Test
    public void test_abnormal() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Number must be positive");
        new FizzBuzz().of(-1);
    }
}
