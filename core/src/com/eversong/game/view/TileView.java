package com.eversong.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.eversong.game.controller.Eversong;
import com.eversong.game.model.Tile;

/**
 * Created by Jacob Lundberg on 2015-04-24.
 */
public class TileView{

    private Sprite sprite;
    private World world;
    private Tile tile;
    private Texture texture;
    private SpriteBatch batch;
    private Body body;
    private float width,height;

    public TileView(World world, Tile tile, Texture texture, SpriteBatch batch, Sprite sprite) {
        this.world = world;
        this.tile = tile;
        this.texture = texture;
        this.batch = batch;
        this.sprite = sprite;
        sprite.setPosition(tile.getX(), tile.getY());
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
    }

    public void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set((tile.getX() +width/2)/ Eversong.SCALE, (tile.getY() + height/2)/Eversong.SCALE);

        body = world.createBody(bodyDef);

        //Create the body as a box
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(width/2 / Eversong.SCALE, height/2 / Eversong.SCALE);

        //Set physical attributes to the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 7f;
        fixtureDef.friction = 1f;

        body.createFixture(fixtureDef);

        //Make the body still when no acceleration are applied
        shape.dispose();
    }
    public void renderBody() {
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();
    }

    public Body getBody() {
        return body;
    }

    public Tile getTile(){ return tile; }
    public float getWidth(){ return width; }
    public float getHeight(){ return height; }
}
