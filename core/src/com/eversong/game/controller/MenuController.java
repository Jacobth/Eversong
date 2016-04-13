package com.eversong.game.controller;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eversong.game.model.Cloud;
import com.eversong.game.model.Timer;
import com.eversong.game.view.MenuView;

/**
 * Created by jacobth on 2015-08-13.
 */
public class MenuController implements ApplicationListener, InputProcessor{

    private MenuView menuView;
    private Eversong eversong;
    private Preferences prefs;
    private Timer timer;
    private Image mute;
    public static boolean isMuted;
    private Texture soundTexture;
    private Texture muteTexture;

    @Override
    public void create() {
        isMuted = false;
        menuView = new MenuView();

        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(menuView.getStage());
        im.addProcessor(this);
        Gdx.input.setInputProcessor(im);

        addListeners();
        prefs = Gdx.app.getPreferences("My Preferences");
        timer = new Timer(1f);

        FileHandle muteFile = Gdx.files.internal("mute.png");
        Texture texture = new Texture(muteFile);
        mute = new Image(texture);

        soundTexture = new Texture(Gdx.files.internal("sound.png"));
        muteTexture = new Texture(Gdx.files.internal("mute.png"));

    }

    public void addListeners() {
        menuView.getPlayButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                eversong = new Eversong();
                eversong.create();
            }
        });
        menuView.getSoundButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                MenuController.isMuted = true;
                menuView.getMuteButton().setSize(muteTexture.getWidth(), muteTexture.getHeight());
                menuView.getSoundButton().setSize(0, 0);
            }
        });
        menuView.getMuteButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                MenuController.isMuted = false;
                menuView.getMuteButton().setSize(0, 0);
                menuView.getSoundButton().setSize(soundTexture.getWidth(), soundTexture.getHeight());
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
            if (timer.hasTimeElapsed()) {
                eversong = null;
                prefs.flush();
            }
            else
                eversong.render();

            InputMultiplexer im = new InputMultiplexer();
            im.addProcessor(menuView.getStage());
            im.addProcessor(this);
            Gdx.input.setInputProcessor(im);
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
        eversong = new Eversong();
        eversong.create();
        return true;
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
