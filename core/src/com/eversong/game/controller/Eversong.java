package com.eversong.game.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.eversong.game.model.Player;
import com.eversong.game.view.EversongView;

import java.util.ArrayList;

/**
 * Created by jaclun on 7/10/2015.
 */
public class Eversong extends Game{

    private EversongView eversongView;

    public static final float SCALE = 100f;

    private World world;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Player player;

    private ArrayList<IController> controllerList;
    private ClickBallController clickBallController;
    private TileController tileWallController;
    private BounceBallController bounceBallController;
    private CollisionController collisionController;

    @Override
    public void create() {
        eversongView = new EversongView();
        eversongView.onCreate();
        world = eversongView.getWorld();
        camera = eversongView.getCamera();
        batch = eversongView.getBatch();

        controllerList = new ArrayList<IController>();

        createClickBall();
        createWalls();
        createBounceBall();

        for (IController controller : controllerList)
            controller.onCreate();

        collisionController = new CollisionController(tileWallController.getWallList(), clickBallController.getBody(), bounceBallController.getBody(), tileWallController.getWallList().get(0));

        controllerList.add(collisionController);
    }

    @Override
    public void render() {
        eversongView.onRender();

        for (IController controller : controllerList)
            controller.onRender();

        world.setContactListener(collisionController);

        eversongView.setDebugRenderer();
    }

    public void createClickBall() {
        player = new Player();

        clickBallController = new ClickBallController(player, batch, world, camera);
        controllerList.add(clickBallController);
    }
    private void createWalls() {
        tileWallController = new TileController(world, batch, camera);

        controllerList.add(tileWallController);
    }
    private void createBounceBall() {
        bounceBallController = new BounceBallController(world, batch);
        controllerList.add(bounceBallController);
    }
}