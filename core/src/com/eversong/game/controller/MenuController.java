package com.eversong.game.controller;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eversong.game.model.Player;
import com.eversong.game.view.MenuView;

/**
 * Created by jacobth on 2015-08-13.
 */
public class MenuController implements ApplicationListener, InputProcessor {

    private MenuView menuView;
    private Eversong eversong;
    private boolean isInFocus = true;

    @Override
    public void create() {
        menuView = new MenuView();

        Gdx.input.setInputProcessor(menuView.getStage());
        addListeners();
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
        } else if (!eversong.getIsGameOver()) {
            eversong.render();
        } else {
            eversong = null;
            Gdx.input.setInputProcessor(menuView.getStage());
        }
    }

    @Override
    public void pause() {

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
