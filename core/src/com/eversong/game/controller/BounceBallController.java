package com.eversong.game.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.eversong.game.model.BounceBall;
import com.eversong.game.model.Player;
import com.eversong.game.view.BounceBallView;

/**
 * Created by jaclun on 7/10/2015.
 */
public class BounceBallController implements IController{

    private BounceBallView bounceBallView;
    private World world;
    private SpriteBatch batch;
    private BounceBall bounceBall;
    private OrthographicCamera camera;
    private Player player;
    private float yDiff;
    private float direction;

    public BounceBallController(World world, SpriteBatch batch, OrthographicCamera camera, Player player, float yDiff, BounceBall bounceBall, float direction) {
        this.world = world;
        this.batch = batch;
        this.camera = camera;
        this.player = player;
        this.bounceBall = bounceBall;
        this.yDiff = yDiff;
        this.direction = direction;
    }

    @Override
    public void onCreate() {
        bounceBallView = new BounceBallView();
        bounceBallView.createBody(world, camera, yDiff, direction);
        bounceBall.setRadius(bounceBallView.getSprite().getHeight() / 2);
    }

    @Override
    public void onRender() {
        bounceBallView.setPosition(bounceBall);
        bounceBallView.renderBall(batch, camera);
    }

    public Body getBody() {
        return bounceBallView.getBody();
    }
    public Sprite getSprite() {
        return bounceBallView.getSprite();
    }
}
