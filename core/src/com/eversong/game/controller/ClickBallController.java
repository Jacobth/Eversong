package com.eversong.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.eversong.game.model.ClickBall;
import com.eversong.game.model.Player;
import com.eversong.game.view.ClickBallView;

/**
 * Created by jaclun on 7/10/2015.
 */
public class ClickBallController implements InputProcessor, IController, GestureDetector.GestureListener{

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

      //  Gdx.input.setInputProcessor(this);
        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);
        im.addProcessor(gd);
        im.addProcessor(this);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void onCreate() {
        clickBallView.createAnimation();
    }

    @Override
    public void onRender() {
        clickBallView.setPosition(clickBall);
        clickBallView.renderBall(batch);
        clickBallView.renderAnimation(batch);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && clickBallView.getBody().getPosition().y > 0 && Eversong.isScore) {
            clickBallView.getBody().setLinearDamping(0f);
          //  clickBallView.getBody().applyLinearImpulse(0f, -7f, clickBallView.getX(), clickBallView.getY(), true);
            clickBallView.getBody().setLinearVelocity(0f, -16f);
            Eversong.isScore = false;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && clickBallView.getBody().getPosition().y < 0 && Eversong.isScore) {
            clickBallView.getBody().setLinearDamping(0f);
          //  clickBallView.getBody().applyLinearImpulse(0f, 7f, clickBallView.getX(), clickBallView.getY(), true);
            clickBallView.getBody().setLinearVelocity(0f, 16f);
            Eversong.isScore = false;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            clickBallView.getBody().setLinearDamping(0f);
            clickBallView.getBody().applyLinearImpulse(-2f, 0f, clickBallView.getX(), clickBallView.getY(), true);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            clickBallView.getBody().setLinearDamping(0f);
            clickBallView.getBody().applyLinearImpulse(2f, 0f, clickBallView.getX(), clickBallView.getY(), true);
        }
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
    public Sprite getSprite() {
        return clickBallView.getSprite();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if(Math.abs(velocityX)>Math.abs(velocityY)){
            if(velocityX>0){
                clickBallView.getBody().setLinearDamping(0f);
                clickBallView.getBody().applyLinearImpulse(4f, 0f, clickBallView.getX(), clickBallView.getY(), true);
            }else{
                clickBallView.getBody().setLinearDamping(0f);
                clickBallView.getBody().applyLinearImpulse(-4f, 0f, clickBallView.getX(), clickBallView.getY(), true);
            }
        }else{
            if(velocityY>0 && clickBallView.getBody().getPosition().y > 0 && Eversong.isScore){
                clickBallView.getBody().setLinearDamping(0f);
                clickBallView.getBody().setLinearVelocity(0f, -16f);
                Eversong.isScore = true;
            }else if(velocityY<0 && clickBallView.getBody().getPosition().y < 0 && Eversong.isScore){
                clickBallView.getBody().setLinearDamping(0f);
                clickBallView.getBody().setLinearVelocity(0f, 16f);
                Eversong.isScore = true;
            }
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
