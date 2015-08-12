package com.eversong.game.controller;

import box2dLight.*;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.Color;
import com.eversong.game.model.Player;

public class LightController implements IController {

    private float dayTime = 0.1f;
    private RayHandler lightHandler;
    private World world;
    private OrthographicCamera camera;

    private box2dLight.PointLight lightBall1;
    private box2dLight.PointLight lightBall2;
    private Player player;

    public LightController(World world, OrthographicCamera camera, Player player) {
        this.world = world;
        this.camera = camera;
        this.player = player;
    }

    @Override
    public void onCreate() {
        RayHandler.setGammaCorrection(false);
        lightHandler = new RayHandler(world);

        lightHandler.setBlurNum(3);

        lightBall1 = new PointLight(lightHandler, 10, Color.DARK_GRAY, 190, 0, 0); //(handler, number of rays, color, "radiuseffect",xPosition, yPosition)
        lightBall2 = new PointLight(lightHandler, 10, Color.BLUE, 100, 0, 0);
    }

    @Override
    public void onRender() {

        //if(dayTime <0.7f)
          //  dayTime = dayTime + 0.003f; //Speed of day will arise
        lightHandler.setAmbientLight(dayTime);

        lightBall1.setPosition(player.getClickBall().getX() + player.getClickBall().getRadius(), player.getClickBall().getY() + player.getClickBall().getRadius());
        lightBall2.setPosition(player.getBounceBall().getX() + player.getBounceBall().getRadius(), player.getBounceBall().getY() + player.getBounceBall().getRadius());
        lightHandler.setCombinedMatrix(camera);
        lightHandler.updateAndRender();
    }
}
