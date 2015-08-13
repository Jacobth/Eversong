package com.eversong.game.controller;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.eversong.game.model.BounceBall;
import com.eversong.game.model.Player;
import com.eversong.game.view.BounceBallView;

import java.util.ArrayList;


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

    public BounceBallController(World world, SpriteBatch batch, OrthographicCamera camera, Player player, float yDiff, BounceBall b) {
        this.world = world;
        this.batch = batch;
        this.camera = camera;
        this.player = player;
        bounceBall = b;
        this.yDiff = yDiff;
    }

    @Override
    public void onCreate() {
        bounceBallView = new BounceBallView();
        bounceBallView.createBody(world, camera, yDiff);
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
