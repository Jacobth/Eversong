package com.eversong.game.model;

/**
 * Created by jaclun on 7/10/2015.
 */
public class Player {
    private ClickBall clickBall;

    public Player() {
        clickBall = new ClickBall();
    }
    public ClickBall getClickBall() {
        return clickBall;
    }
}
