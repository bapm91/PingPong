package com.exemple.kulynych.model;

import com.exemple.kulynych.model.ball.Ball;

public interface Physics {
    public void tick(long time);
    public void collision(Ball ball);
    public void collision(Wall wall);
}
