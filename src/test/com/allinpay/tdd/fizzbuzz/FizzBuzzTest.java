package com.allinpay.tdd.fizzbuzz;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_normal() {
        assertThat(new FizzBuzz(1).of()).isEqualTo("1");
        assertThat(new FizzBuzz(3).of()).isEqualTo("Fizz");
        assertThat(new FizzBuzz(5).of()).isEqualTo("Buzz");
        assertThat(new FizzBuzz(15).of()).isEqualTo("FizzBuzz");
        assertThat(new FizzBuzz(13).of()).isEqualTo("Fizz");
        assertThat(new FizzBuzz(52).of()).isEqualTo("Buzz");
    }

    @Test
    public void test_abnormal() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Number must be positive");
        new FizzBuzz(-1).of();
    }
}
