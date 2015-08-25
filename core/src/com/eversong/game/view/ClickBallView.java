package com.eversong.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.eversong.game.controller.Eversong;
import com.eversong.game.model.ClickBall;
import com.eversong.game.model.Player;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by jaclun on 7/10/2015.
 */
public class ClickBallView {
    private Sprite sprite;
    private Sprite shieldSprite;
    private Body body;
    private Texture shieldTexture;
    private Player player;

    private Sprite sprite2;

    private static final int FRAME_COLS = 1;
    private static final int FRAME_ROWS = 6;

    private Animation walkAnimation;
    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;

    float stateTime;

    private ClickBall clickBall;


    public void createBody(Player player, World world, Camera camera) {
        this.player = player;
       // sprite = new Sprite(new Texture("ball.png"));

        sprite = new Sprite(new Texture("ball.png"));

        clickBall = player.getClickBall();
        clickBall.setPosition(0- sprite.getWidth()/2, camera.viewportHeight/2- sprite.getHeight());


        sprite.setPosition(clickBall.getX(), clickBall.getY());

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set((sprite.getX() +sprite.getWidth()/2)/ Eversong.SCALE, (sprite.getY() + sprite.getHeight()/2)/Eversong.SCALE);

        body = world.createBody(bodyDef);

        //Create the body as a circle
        CircleShape shape = new CircleShape();

        shape.setRadius(sprite.getWidth()/(2* Eversong.SCALE));

        //Set physical attributes to the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2f;
        fixtureDef.friction = 1f;
        //Make the ball bounce on other bodies
        fixtureDef.restitution = 1f;

        body.createFixture(fixtureDef);

        shape.dispose();

        sprite.setSize(sprite.getWidth()*1.22f, sprite.getHeight());
    }

    public void renderBall(SpriteBatch batch) {
     /*   batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();*/
       // body.setLinearDamping(1f);
    }

    public void setPosition(ClickBall ball) {
        ball.setPosition(body.getPosition().x * Eversong.SCALE - sprite.getWidth() / 2, body.getPosition().y * Eversong.SCALE - sprite.getHeight() / 2);
        sprite.setPosition(ball.getX(), ball.getY());
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }
    public Body getBody() {
        return body;
    }
    public Sprite getSprite() {
        return sprite;
    }

    public void createAnimation() {
        walkSheet = new Texture(Gdx.files.internal("birds2.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.04f, walkFrames);
        stateTime = 0f;
    }

    public void renderAnimation(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        sprite2 = new Sprite(currentFrame);
        sprite2.setPosition(clickBall.getX(), clickBall.getY());
        batch.begin();
        batch.draw(sprite2, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();
    }
}
