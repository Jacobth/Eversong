package com.eversong.game.model;

import java.util.ArrayList;

/**
 * Created by jaclun on 7/10/2015.
 */
public class Player {
    private ClickBall clickBall;
    private BounceBall bounceBallUp;
    private BounceBall bounceBallDown;
    private int score;
    private ArrayList<BounceBall> bounceBalls;

    public Player() {
        clickBall = new ClickBall();
        bounceBallUp = new BounceBall();
        bounceBallDown = new BounceBall();
        score = 0;
        bounceBalls = new ArrayList<BounceBall>();
        bounceBalls.add(bounceBallUp);
        bounceBalls.add(bounceBallDown);
    }
    public ClickBall getClickBall() {
        return clickBall;
    }
  //  public BounceBall getBounceBallUp() {
  //      return bounceBall;
   // }
    public ArrayList<BounceBall> getBounceBalls() {
        return bounceBalls;
    }


    public int getScore() {
        return score;
    }
    public void addScore() {
        score++;
    }
}
