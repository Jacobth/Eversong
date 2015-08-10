package com.eversong.game.model;

/**
 * Created by jaclun on 7/10/2015.
 */
public class Player {
    private ClickBall clickBall;
    private int score;

    public Player() {
        clickBall = new ClickBall();
        score = 0;
    }
    public ClickBall getClickBall() {
        return clickBall;
    }

    public int getScore() {
        return score;
    }
    public void addScore() {
        score++;
    }
}
