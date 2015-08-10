package com.eversong.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.eversong.game.model.ClickBall;
import com.eversong.game.model.Player;
import com.eversong.game.view.ClickBallView;

/**
 * Created by jaclun on 7/10/2015.
 */
public class ClickBallController implements InputProcessor, IController{

    protected Body body;
    protected ClickBallView clickBallView;
    protected SpriteBatch batch;

    private Player player;
    private ClickBall clickBall;
    private OrthographicCamera camera;

    public ClickBallController(Player player, SpriteBatch batch, World world, OrthographicCamera camera) {
        this.player = player;
        this.batch = batch;
        this.clickBall = player.getClickBall();
        this.camera = camera;

        clickBallView = new ClickBallView();
        clickBallView.createBody(player, world, camera);
        this.body = clickBallView.getBody();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onRender() {
        clickBallView.setPosition(clickBall);
        //touchDown(0,0,0,0);
        clickBallView.renderBall(batch);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            clickBallView.getBody().setLinearDamping(0f);
            clickBallView.getBody().applyLinearImpulse(0f, -7f, clickBallView.getX(), clickBallView.getY(), true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            clickBallView.getBody().setLinearDamping(0f);
            clickBallView.getBody().applyLinearImpulse(0f, 7f, clickBallView.getX(), clickBallView.getY(), true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            clickBallView.getBody().setLinearDamping(0f);
            clickBallView.getBody().applyLinearImpulse(-2f, 0f, clickBallView.getX(), clickBallView.getY(), true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            clickBallView.getBody().setLinearDamping(0f);
            clickBallView.getBody().applyLinearImpulse(2f, 0f, clickBallView.getX(), clickBallView.getY(), true);
        }
        //clickBallView.getBody().setTransform(Gdx.input.getX()/Eversong.SCALE-(camera.viewportWidth/2/Eversong.SCALE), -Gdx.input.getY()/Eversong.SCALE+(camera.viewportHeight/2/Eversong.SCALE), clickBallView.getBody().getAngle());
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public Body getBody() {
        return clickBallView.getBody();
    }
}
