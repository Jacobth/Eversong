package com.eversong.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.eversong.game.model.BounceBall;
import com.eversong.game.view.BounceBallView;


/**
 * Created by jaclun on 7/10/2015.
 */
public class BounceBallController implements IController{

    private BounceBallView bounceBallView;
    private World world;
    private SpriteBatch batch;
    private BounceBall bounceBall;

    public BounceBallController(World world, SpriteBatch batch) {
        this.world = world;
        this.batch = batch;
        bounceBall = new BounceBall();
    }

    @Override
    public void onCreate() {
        bounceBallView = new BounceBallView();
        bounceBallView.createBody(world);
    }

    @Override
    public void onRender() {
        bounceBallView.setPosition(bounceBall);
        bounceBallView.renderBall(batch);
    }

    public Body getBody() {
        return bounceBallView.getBody();
    }
}
