package com.exemple.kulynych.model;

import com.exemple.kulynych.model.ball.Ball;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

public class World extends Component {
    public List<Physics> figure = new LinkedList<>();
    private int width;
    private int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void tick(long time) {
        for (int i = 0; i < figure.size(); i++) {
            if (figure.get(i) instanceof Wall) {
                continue;
            }
            Ball ballA = (Ball) figure.get(i);
            for (int j = 0; j < figure.size(); j++) {
                if (figure.get(j) instanceof Ball) {
                    if (j > i)
                        ballA.collision(figure.get(j));
                }
                if (figure.get(j) instanceof Wall) {
                    figure.get(j).collision(ballA);
                }
            }
        }
        for (Physics physic : figure) {
            physic.tick(time); //     polymorphism!!!
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}