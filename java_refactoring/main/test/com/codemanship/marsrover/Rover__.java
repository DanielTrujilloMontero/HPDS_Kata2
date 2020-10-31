package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.Rover;
import refactoring.Rover.Position;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.Rover.Heading.*;
import static refactoring.Rover.Order.*;


public class Rover__ {

    @Test
    public void could_be_initialized_with_legacy_constructor() {
        assertThat(new Rover("N", 5, 5).heading()).isEqualTo(North);
        assertThat(new Rover("North", 5, 5).heading()).isEqualTo(North);
        assertThat(new Rover("North", 5, 5).position()).isEqualTo(new Position(5,5));
    }

    @Test
    public void could_be_initialized_using_new_constructors() {
        assertThat(new Rover(North, new Position(4,4)).position()).isEqualTo(new Position(4,4));
        assertThat(new Rover(North, 4, 4).position()).isEqualTo(new Position(4,4));
    }

    @Test
    public void could_turn_left() {
        Rover rover = new Rover(North, new Position(3, 3));
        rover.go(Left);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,3));
    }

    @Test
    public void could_turn_right() {
        Rover rover = new Rover(East, new Position(5, 1));
        rover.go(Right);
        assertThat(rover.heading()).isEqualTo(South);
        assertThat(rover.position()).isEqualTo(new Position(5,1));
    }

    @Test
    public void could_go_forward() {
        Rover rover = new Rover(South, new Position(-1, 1));
        rover.go(Forward);
        assertThat(rover.heading()).isEqualTo(South);
        assertThat(rover.position()).isEqualTo(new Position(-1,0));
    }

    @Test
    public void could_go_backward() {
        Rover rover = new Rover(West, new Position(-4, 4));
        rover.go(Backward);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(-3,4));
    }

    @Test
    public void could_execute_many_orders() {
        Rover rover = new Rover(West, new Position(3, 1));
        rover.go(Backward, Left, Forward, Right, Forward);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_execute_legacy_instructions() {
        Rover rover = new Rover(West, new Position(3, 1));
        rover.go("BLFRF");
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_ignore_legacy_instructions() {
        Rover rover = new Rover(West, new Position(3, 1));
        rover.go("BL*FRF");
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,0));
    }
}
