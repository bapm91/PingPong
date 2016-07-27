package com.exemple.kulynych.model;

public class World {
    private Ball ball;
    private int width;
    private int height;
    Platform platform;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.ball = new Ball(this, 20);
    }
}
