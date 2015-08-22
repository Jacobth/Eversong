package com.eversong.game.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eversong.game.controller.Eversong;
import com.eversong.game.model.Cloud;

import java.util.Random;


/**
 * Created by jacobth on 2015-08-22.
 */
public class CloudView {

    private Sprite sprite;
    private Cloud cloud;
    private Camera camera;
    private float incX;
    private float randomY;
    private SpriteBatch batch;

    public CloudView(Camera camera, SpriteBatch batch, float x) {
        Texture cloudTexture = new Texture("android/assets/cloud.png");
        sprite = new Sprite(cloudTexture);
        cloud = new Cloud();
        this.camera = camera;
        randomY = randomY();
        cloud.setPostion(x - sprite.getWidth(), randomY);
        incX = x - sprite.getWidth();
        this.batch = batch;
    }
    public void renderCloud() {
        if(cloud.getX() > camera.viewportWidth/2) {
            randomY = randomY();
            incX = -camera.viewportWidth/2 - sprite.getWidth();
            cloud.setPostion(-camera.viewportWidth/2 - sprite.getWidth(), randomY());
        }
        else if(cloud.getX() < camera.viewportWidth * 0.60f) {
            incX = incX + 1f;
            cloud.setPostion(incX, randomY);
        }

        sprite.setPosition(cloud.getX(), cloud.getY());
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
    private float randomY() {
        Random r = new Random();
        int High = (int)(camera.viewportHeight/2 - sprite.getHeight());
        int Low = -(int)camera.viewportHeight/2;
        float random = r.nextInt(High-Low) + Low;

        return random;
    }

}
