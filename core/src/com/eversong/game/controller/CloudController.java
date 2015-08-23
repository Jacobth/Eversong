package com.eversong.game.controller;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eversong.game.view.CloudView;

/**
 * Created by jacobth on 2015-08-22.
 */
public class CloudController implements IController{

    private CloudView cloudView;

    public CloudController(Camera camera, SpriteBatch batch, float x) {
        cloudView = new CloudView(camera, batch, x);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onRender() {
        cloudView.renderCloud();
    }

    public void draw(){
        cloudView.draw();
    }
}
