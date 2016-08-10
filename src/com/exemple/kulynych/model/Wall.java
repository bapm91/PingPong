package com.exemple.kulynych.model;

import com.exemple.kulynych.model.ball.Ball;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Wall implements Physics {
    private World world;
    private Point wallPosition;
    private int rectHeight;
    private int rectWidth;
    private int rightSide;
    private int topSide;
    private int leftSide;
    private int bottomSide;
    private int numberOfCollision = 0;
    private Point center = new Point(0, 0);

    public Wall(World world, int rectWidth, int rectHeight) {
        this.world = world;
        this.rectWidth = rectWidth;
        this.rectHeight = rectHeight;
        wallPosition();
        center();
        sides();
    }

    private void wallPosition() {
        Position position = new Position();
        wallPosition = new Point(0, 0);
        wallPosition = position.position(world, rectWidth, rectHeight);
    }

    private void center() {
        center.x = wallPosition.x + rectWidth / 2;
        center.y = wallPosition.y + rectHeight / 2;
    }

    private void sides() {
        rightSide = wallPosition.x + rectWidth;
        topSide = wallPosition.y;
        leftSide = wallPosition.x;
        bottomSide = wallPosition.y + rectHeight;
    }

    public boolean contains(Ball ball) {
        int radius = ball.getDiameter() / 2;
        List<Point> list = new ArrayList<>();
        list.add(new Point(ball.getCenter().x + radius, ball.getCenter().y));
        list.add(new Point(ball.getCenter().x, ball.getCenter().y + radius));
        list.add(new Point(ball.getCenter().x, ball.getCenter().y - radius));
        list.add(new Point(ball.getCenter().x - radius, ball.getCenter().y));
        list.add(ball.getCenter());

        for (Point point : list) {
            if (contains(point)) {
                return true;
            }
        }
        return false;
    }

    private boolean contains(Point point) {
        return point.x <= rightSide
                && point.x >= leftSide
                && point.y <= bottomSide
                && point.y >= topSide;
    }

    public String relatively(Ball ball) {

        if (!this.contains(ball)) {
            return "Do nothing";
        }

        Point ballCenter = ball.getCenter();
        if (ballCenter.x == this.center.x && ballCenter.y == this.center.y) {
            return "Right";
        }
        if (contains(ballCenter)) {
            if (ballCenter.x >= this.center.x && ballCenter.y >= this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) <= Math.abs(ballCenter.y - bottomSide)) {
                    return "Right";
                } else {
                    return "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y >= this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) <= Math.abs(ballCenter.y - bottomSide)) {
                    return "Left";
                } else {
                    return "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) <= Math.abs(ballCenter.y - topSide)) {
                    return "Left";
                } else {
                    return "Top";
                }
            }
            if (ballCenter.x >= this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) <= Math.abs(ballCenter.y - topSide)) {
                    return "Right";
                } else {
                    return "Top";
                }
            }
        }
        if (!contains(ballCenter)) {
            if (ballCenter.x >= this.center.x && ballCenter.y >= this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) >= Math.abs(ballCenter.y - bottomSide)) {
                    return "Right";
                } else {
                    return "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y >= this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) >= Math.abs(ballCenter.y - bottomSide)) {
                    return "Left";
                } else {
                    return "Bottom";
                }
            }
            if (ballCenter.x < this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - leftSide) >= Math.abs(ballCenter.y - topSide)) {
                    return "Left";
                } else {
                    return "Top";
                }
            }
            if (ballCenter.x >= this.center.x && ballCenter.y < this.center.y) {
                if (Math.abs(ballCenter.x - rightSide) >= Math.abs(ballCenter.y - topSide)) {
                    return "Right";
                } else {
                    return "Top";
                }
            }
        }
        return "Do nothing";
    }

    public void detectWallCollision(Ball ball) {
        numberOfCollision++;
        if (numberOfCollision >= 4) {
            world.figure.remove(this);
        }
        if (this.contains(ball)) {
            if (this.relatively(ball).equals("Right")) {
                ball.setSpeed(Math.abs(ball.getSpeed().x), ball.getSpeed().y);
            }
            if (this.relatively(ball).equals("Left")) {
                ball.setSpeed(-Math.abs(ball.getSpeed().x), ball.getSpeed().y);
            }
            if (this.relatively(ball).equals("Top")) {
                ball.setSpeed(ball.getSpeed().x, -Math.abs(ball.getSpeed().y));
            }
            if (this.relatively(ball).equals("Bottom")) {
                ball.setSpeed(ball.getSpeed().x, Math.abs(ball.getSpeed().y));
            }
        }
    }

    public Point getWallPosition() {
        return wallPosition;
    }

    public int getRectHeight() {
        return rectHeight;
    }

    public int getRectWidth() {
        return rectWidth;
    }

    @Override
    public void tick(long time) {
    }

    @Override
    public void collision(Physics figure) {
        if (this.contains((Ball) figure)) {
            detectWallCollision((Ball) figure);
        }
    }
}
