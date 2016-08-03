package com.exemple.kulynych.model;

import sun.java2d.cmm.ColorTransform;

import java.awt.*;
import java.util.Map;

public class Ball {
    private World world;

    // free fall acceleration, in pixels/s**2. TODO: experiment.
    private final Point G = new Point(0, 400);
    private int diametr;
    private int minSpeed = 100;
    private long timestamp = 0;

    public Point coordinates;

    public Ball(World world, int diametr) {
        this.world = world;
        this.diametr = diametr;
        Position position = new Position();
        coordinates = position.position(world, diametr, diametr);
    }

    // in pixels/s
    Point speed = new Point(
            minSpeed + (int) (Math.random() * 200),
            minSpeed + (int) (Math.random() * 200));

    public void calculateNewVelocities(Ball ballFirst, Ball ballSecond) {
        int mass1 = ballFirst.getDiametr() / 2;
        int mass2 = ballSecond.getDiametr() / 2;
        int velX1 = ballFirst.getSpeed().x;
        int velX2 = ballSecond.getSpeed().x;
        int velY1 = ballFirst.getSpeed().y;
        int velY2 = ballSecond.getSpeed().y;

        int newVelX1 = (velX1 * (mass1 - mass2) + (2 * mass2 * velX2)) / (mass1 + mass2);
        int newVelX2 = (velX2 * (mass2 - mass1) + (2 * mass1 * velX1)) / (mass1 + mass2);
        int newVelY1 = (velY1 * (mass1 - mass2) + (2 * mass2 * velY2)) / (mass1 + mass2);
        int newVelY2 = (velY2 * (mass2 - mass1) + (2 * mass1 * velY1)) / (mass1 + mass2);

        ballFirst.setSpeed(newVelX1, newVelY1);
        ballSecond.setSpeed(newVelX2, newVelY2);

//        ballFirst.coordinates.x = ballFirst.coordinates.x + newVelX1;
//        ballFirst.coordinates.y = ballFirst.coordinates.y + newVelY1;
//        ballSecond.coordinates.x = ballSecond.coordinates.x + newVelX2;
//        ballSecond.coordinates.y = ballSecond.coordinates.y + newVelY2;
    }

    // Calculate the new Ball's coordinates and speed at the given moment "timestamp"
    public void tick(long timestamp) {
        if (this.timestamp == 0) {
            this.timestamp = timestamp;
            return;
        }

        // gravity
        long dt = timestamp - this.timestamp;
        int dsy = (int) (G.y * dt / 1000);
        this.speed.y = this.speed.y + dsy;

        if (this.coordinates.y >= world.getHeight() - diametr / 2) {
            this.speed.y = -Math.abs(this.speed.y) * 95 / 100;
        }
        if (this.coordinates.y <= diametr / 2) {
            this.speed.y = Math.abs(this.speed.y) * 95 / 100;
        }
        if (this.coordinates.x >= world.getWidth() - diametr / 2) {
            this.speed.x = -Math.abs(this.speed.x) * 95 / 100;
        }
        if (this.coordinates.x <= diametr / 2) {
            this.speed.x = Math.abs(this.speed.x) * 95 / 100;
        }

        if (this.coordinates.x >= world.getWidth() + diametr * 2) {
            this.coordinates.x = Position.rePositionX(world);
        }
        if (this.coordinates.y >= world.getHeight() + diametr * 2) {
            this.coordinates.y = Position.rePositionY(world);
        }

        // coordinates
        this.coordinates.x = (int) (this.coordinates.x + this.speed.x * dt / 1000);
        this.coordinates.y = (int) (this.coordinates.y + this.speed.y * dt / 1000);

        this.timestamp = timestamp;
    }

    public void detectCollisions(long time) {
        int radius = diametr / 2;

        for (int i = 0; i < world.balls.size(); i++) {
            int radiusSecondBall = world.balls.get(i).getDiametr() / 2;
            if (world.balls.get(i) != this) {
                if (coordinates.x + radius + radiusSecondBall > world.balls.get(i).coordinates.x
                        && coordinates.x < world.balls.get(i).coordinates.x + radius + radiusSecondBall
                        && coordinates.y + radius + radiusSecondBall > world.balls.get(i).coordinates.y
                        && coordinates.y < world.balls.get(i).coordinates.y + radius + radiusSecondBall) {
                    if (distanceTo(this, world.balls.get(i)) < radius + radiusSecondBall) {
//                        drawCollision(this, world.balls.get(i));
                        calculateNewVelocities(this, world.balls.get(i));
                    }
                }
            }
        }
        this.tick(time);
    }

    public Double distanceTo(Ball ballFirst, Ball ballSecond) {
        double distance;
        distance = Math.sqrt(
                (ballFirst.coordinates.x - ballSecond.coordinates.x)
                        * (ballFirst.coordinates.x - ballSecond.coordinates.x)
                        + (ballFirst.coordinates.y - ballSecond.coordinates.y)
                        * (ballFirst.coordinates.y - ballSecond.coordinates.y));
        if (distance < 0) { distance = distance * -1; }
        return distance;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "coordinates=" + coordinates +
                ", speed=" + speed +
                '}';
    }

    public int getDiametr() {
        return diametr;
    }

    public Point getSpeed() {
        return speed;
    }

    public void setSpeed(int x, int y){
        this.speed.x = x;
        this.speed.y = y;
    }
}
