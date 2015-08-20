package com.eversong.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.eversong.game.model.Player;

import java.util.ArrayList;

/**
 * Created by jaclun on 7/10/2015.
 */
public class CollisionController implements ContactListener, IController{

    private ArrayList<Body> tileList;
    private Sound sound;
    private Player player;
    private Body body;
    private Body bounce1;
    private Body bounce2;
    private Body upperWall;
    private Body downWall;
    private BitmapFont font;
    private SpriteBatch batch;
    private boolean gameOver = false;

    public CollisionController(ArrayList<Body> tileList,Body body, Body bounce1, Body bounce2, Body downWall, Body upperWall, Player player, SpriteBatch batch) {
        this.tileList = tileList;
        this.body = body;
        this.bounce1 = bounce1;
        this.bounce2 = bounce2;
        this.downWall = downWall;
        this.upperWall = upperWall;
        this.player = player;
        this.batch = batch;

        FileHandle collisionFileHandle = Gdx.files.internal("android/assets/sounds/eversonghit.mp3");
        //FileHandle collisionFileHandle = Gdx.files.internal("sounds/eversonghit.mp3");
        sound = Gdx.audio.newSound(collisionFileHandle);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onRender() {

    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if(a == body && b == bounce1) {
                body.applyForceToCenter(2f, -30f, false);
                gameOver = true;
        }
        else if(a == body && b == bounce2) {
                body.applyForceToCenter(1f, 30f, false);
                gameOver = true;
        }

      else if(a == downWall && b == body) {
            sound.play();
            if(!gameOver) {
                body.setLinearDamping(40f);
                player.addScore();
            }
        }
        else if(a == upperWall && b == body) {
            sound.play();
            if(!gameOver) {
                body.setLinearDamping(40f);
                player.addScore();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public boolean isGameOver() {
        return gameOver;
    }
}

