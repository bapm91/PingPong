package com.exemple.kulynych.model;

import com.exemple.kulynych.model.ball.Ball;

public interface Physics {

    void tick(long time);

    void collision(Physics figure);
}
