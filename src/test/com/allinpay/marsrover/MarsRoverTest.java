package com.allinpay.marsrover;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverTest {
    @Test
    public void should_return_self_position() {
        String commandLine = "area:100,100 init:10,20,E move:f,20 turn:r";
        MarsRover marsRover = new MarsRover(commandLine);
        LandPoint landPoint = marsRover.getCurrentPosition();
        assertThat(landPoint).hasFieldOrPropertyWithValue("xAxis", 30)
                .hasFieldOrPropertyWithValue("yAxis", 20)
                .hasFieldOrPropertyWithValue("orientation", "S");
    }
}
