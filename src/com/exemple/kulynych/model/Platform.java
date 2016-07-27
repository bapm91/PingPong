package com.exemple.kulynych.model;

public class Platform {
    int top;
    int left;
    int width;
    int height;

    public Platform(int top, int left, int width, int height) {
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }

    public int getLeft() { return left; }
    public int getRight() { return left + width; }
}
