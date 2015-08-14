package com.eversong.game.controller;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eversong.game.model.Player;
import com.eversong.game.model.Timer;
import com.eversong.game.view.MenuView;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;

/**
 * Created by jacobth on 2015-08-13.
 */
public class MenuController implements ApplicationListener, InputProcessor{

    private MenuView menuView;
    private Eversong eversong;
    private Preferences prefs;
    private Timer timer;


    @Override
    public void create() {
        menuView = new MenuView();
        Gdx.input.setInputProcessor(menuView.getStage());
        addListeners();
        prefs = Gdx.app.getPreferences("My Preferences");
        timer = new Timer(1f);
    }

    public void addListeners() {
        menuView.getPlayButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                eversong = new Eversong();
                eversong.create();
            }
        });
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        if (eversong == null) {
            menuView.update();
            Eversong.highScore = prefs.getInteger("score", 0);
            timer = new Timer(1f);
        } else if (!eversong.getIsGameOver()) {
            eversong.render();
        } else {
            setHighScore();
            timer.update(Gdx.graphics.getDeltaTime());
            if (timer.hasTimeElapsed())
            eversong = null;
            else
                eversong.render();
            Gdx.input.setInputProcessor(menuView.getStage());
        }
    }

    private void setHighScore() {
        if(Eversong.highScore < eversong.getHighScore()) {
            Eversong.highScore = eversong.getHighScore();
            prefs.putInteger("score", Eversong.highScore);
        }
    }

    @Override
    public void pause() {
        prefs.flush();
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

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

}
