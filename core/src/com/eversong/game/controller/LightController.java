package com.eversong.game.controller;

import box2dLight.*;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.Color;
import com.eversong.game.model.BounceBall;
import com.eversong.game.model.Player;

public class LightController implements IController {

    private float dayTime = 1f;
    private RayHandler lightHandler;
    private World world;
    private OrthographicCamera camera;

    private box2dLight.PointLight lightBall1;
    private box2dLight.PointLight lightBall2;
    private box2dLight.PointLight lightBall3;
    private Player player;
    private BounceBall ball1;
    private BounceBall ball2;

    public LightController(World world, OrthographicCamera camera, Player player, BounceBall ball1, BounceBall ball2) {
        this.world = world;
        this.camera = camera;
        this.player = player;
        this.ball1 = ball1;
        this.ball2 = ball2;
    }

    @Override
    public void onCreate() {
        RayHandler.setGammaCorrection(false);
        lightHandler = new RayHandler(world);

        lightHandler.setBlurNum(3);

        lightBall1 = new PointLight(lightHandler, 10, Color.DARK_GRAY, 190, 0, 0); //(handler, number of rays, color, "radiuseffect",xPosition, yPosition)
        lightBall2 = new PointLight(lightHandler, 10, Color.DARK_GRAY, 100, 0, 0);
        lightBall3 = new PointLight(lightHandler, 10, Color.DARK_GRAY, 100, 0, 0);
    }

    @Override
    public void onRender() {

        if(dayTime >0.9f)
            dayTime = dayTime - 0.006f; //Speed of day will arise
        lightHandler.setAmbientLight(dayTime);

        //lightBall1.setPosition(player.getClickBall().getX() + player.getClickBall().getRadius()*1.5f, player.getClickBall().getY() + player.getClickBall().getRadius());
        lightBall2.setPosition(ball1.getX() + ball1.getRadius(), ball1.getY() + ball1.getRadius());
        lightBall3.setPosition(ball2.getX() + ball2.getRadius(), ball2.getY() + ball2.getRadius());
        lightHandler.setCombinedMatrix(camera);
        lightHandler.updateAndRender();
    }
}
