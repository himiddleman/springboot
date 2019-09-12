package com.allinpay.tdd.marsrover;

import com.allinpay.tdd.marsrover.entity.LandPoint;
import com.allinpay.tdd.marsrover.entity.MarsRover;
import com.allinpay.tdd.marsrover.entity.SouthType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 重构：使用多态替换switch
 * 本质上是将分支代码分散到多个子类中
 */
public class MarsRoverTest {
    @Test
    public void should_return_self_position() {
        String commandLine = "area:100,100 init:10,20,E move:f,20 turn:r";
        MarsRover marsRover = new MarsRover(commandLine);
        LandPoint landPoint = marsRover.getCurrentPosition();
        assertThat(landPoint).hasFieldOrPropertyWithValue("xAxis", 30)
                .hasFieldOrPropertyWithValue("yAxis", 20);
        assertThat(landPoint.getOrientType()).isInstanceOf(SouthType.class);
    }
}
