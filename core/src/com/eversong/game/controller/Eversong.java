package com.eversong.game.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.eversong.game.model.BounceBall;
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
    private BounceBall ball1;
    private BounceBall ball2;
    private BitmapFont font;
    private boolean isGameOver = false;

    private ArrayList<IController> controllerList;
    private ClickBallController clickBallController;
    private TileController tileWallController;
    private BounceBallController bounceBallController;
    private BounceBallController bounceBallController2;
    private CollisionController collisionController;
    private Texture bounceTexture;

    public static int highScore = 0;

    @Override
    public void create() {
        eversongView = new EversongView();
        eversongView.onCreate();
        world = eversongView.getWorld();
        camera = eversongView.getCamera();
        batch = eversongView.getBatch();
       // bounceTexture = new Texture("bounce.png");
        bounceTexture = new Texture("android/assets/bounce2.png");
        controllerList = new ArrayList<IController>();

        //font = new BitmapFont(Gdx.files.internal("test.fnt"));
        font = new BitmapFont(Gdx.files.internal("android/assets/test.fnt"));

        createClickBall();
        createWalls();
        createBounceBall();

        for (IController controller : controllerList)
            controller.onCreate();

        controllerList.add(new LightController(world, camera,player, ball1, ball2));
        controllerList.get(controllerList.size()-1).onCreate();
        collisionController = new CollisionController(tileWallController.getWallList(), clickBallController.getBody(), bounceBallController.getBody(), bounceBallController2.getBody(),
                tileWallController.getWallList().get(0), tileWallController.getWallList().get(1), player, batch);

        controllerList.add(collisionController);
    }

    @Override
    public void render() {
        eversongView.onRender();

        for (IController controller : controllerList)
            controller.onRender();

        world.setContactListener(collisionController);

        draw();

        isGameOver = collisionController.isGameOver();

       // eversongView.setDebugRenderer();
    }

    public void draw() {
        batch.begin();
        font.draw(batch, player.getScore() + "", -camera.viewportWidth / 2 + font.getSpaceWidth(), camera.viewportHeight/2 - font.getSpaceWidth());
        batch.end();
    }

    public void createClickBall() {
        player = new Player();

        clickBallController = new ClickBallController(player, batch, world, camera);
        controllerList.add(clickBallController);
        player.getClickBall().setRadius(clickBallController.getSprite().getHeight()/2);
    }
    private void createWalls() {
        tileWallController = new TileController(world, batch, camera);

        controllerList.add(tileWallController);
    }
    private void createBounceBall() {
        ball1 = new BounceBall();
        ball2 = new BounceBall();
        bounceBallController = new BounceBallController(world, batch, camera , player, bounceTexture.getHeight(), ball1, -1);
        bounceBallController2 = new BounceBallController(world, batch, camera , player, -bounceTexture.getHeight(), ball2, 1);
        controllerList.add(bounceBallController);
        controllerList.add(bounceBallController2);
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public int getHighScore() {
        return player.getScore();
    }
}
