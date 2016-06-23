package com.berkgokden.chess;

/**
 * Location represents the x,y coordinates on a chess board
 */
public class Location {
    private int x;
    private int y;
    public Location(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0)
            throw new IllegalArgumentException("X should be positive.");
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0)
            throw new IllegalArgumentException("Y should be positive.");
        this.y = y;
    }
}
