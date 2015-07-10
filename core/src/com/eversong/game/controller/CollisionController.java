package com.eversong.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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
    private String message;
    private Body body;
    private Body bounce;
    private Body wall;

    public CollisionController(ArrayList<Body> tileList,Body body, Body bounce, Body wall) {
        this.tileList = tileList;
        this.body = body;
        this.bounce = bounce;
        this.wall = wall;

        //  FileHandle collisionFileHandle = Gdx.files.internal("sounds/collision.mp3");
        // sound = Gdx.audio.newSound(collisionFileHandle);
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

        if(a == body && b == bounce) {
            b.applyLinearImpulse(0.3f, 1.1f, b.getPosition().x, b.getPosition().y, true);
            System.out.println("Sucess");
        }
        else if(a == bounce && b == body) {
            a.applyLinearImpulse(0.3f, 1.1f, a.getPosition().x, a.getPosition().y, true);
        }
        else if(a == bounce && b == wall) {
            System.out.println("fail");
        }
        else if(a == wall && b == bounce) {
            System.out.println("fail");
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
}

