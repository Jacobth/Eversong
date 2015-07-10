package com.eversong.game.model;

/**
 * Created by jaclun on 7/10/2015.
 */
public class Tile {

    private float x;
    private float y;

    public Tile(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
