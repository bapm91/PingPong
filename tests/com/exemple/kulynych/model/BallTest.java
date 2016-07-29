package com.exemple.kulynych.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BallTest {
    World world;
    Ball ball;
    
    @Before
    public void setUp() throws Exception {
        world = new World(100, 100);
       // ball = world.ball;
    }

    @Test
    public void tick_floorJump() throws Exception {
        ball.coordinates.y = 80;
        ball.speed.y = 300;
        ball.tick(0);
        ball.tick(100); //ms
        assertEquals(100, ball.coordinates.y);
        assertEquals(320, ball.speed.y);
    }

    @Test
    public void tick_platformBelow() throws Exception {

    }

}