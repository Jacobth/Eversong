package com.eversong.game.model;

/**
 * Created by jaclun on 7/10/2015.
 */
public class ClickBall {
    private float x;
    private float y;

    public ClickBall() {

    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

