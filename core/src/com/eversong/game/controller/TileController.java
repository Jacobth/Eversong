package com.eversong.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.eversong.game.model.Tile;
import com.eversong.game.view.TileView;

import java.util.ArrayList;
/**
 * Created by jaclun on 7/10/2015.
 */
public class TileController implements IController {

    private ArrayList<TileView> tileViewList;

    public TileController(World world, SpriteBatch batch, Camera camera) {

        FileHandle horizontalFileHandle = Gdx.files.internal("wallHorizontal.png");
        Texture horizontalTexture = new Texture(horizontalFileHandle);

        FileHandle verticalFileHandle = Gdx.files.internal("wallVertical.png");
        Texture verticalTexture = new Texture(verticalFileHandle);
/*
        FileHandle horizontalFileHandle = Gdx.files.internal("android/assets/wallHorizontal.png");
        Texture horizontalTexture = new Texture(horizontalFileHandle);

        FileHandle verticalFileHandle = Gdx.files.internal("android/assets/wallVertical.png");
        Texture verticalTexture = new Texture(verticalFileHandle);
*/
        Sprite vertical = new Sprite(verticalTexture);
        Sprite horizontal = new Sprite(horizontalTexture);

        vertical.setSize(0, verticalTexture.getHeight());
        horizontal.setSize(horizontalTexture.getWidth(), 0);

        Tile downTile = new Tile(-camera.viewportWidth / 2, -camera.viewportHeight / 2);
        Tile upTile = new Tile(-camera.viewportWidth / 2, camera.viewportHeight / 2);
        Tile leftTile = new Tile(-camera.viewportWidth / 2, -camera.viewportHeight / 2);
        Tile rightTile = new Tile(camera.viewportWidth / 2, -camera.viewportHeight / 2);

        tileViewList = new ArrayList<TileView>();
        tileViewList.add(new TileView(world, downTile, horizontalTexture, batch, horizontal));
        tileViewList.add(new TileView(world, upTile, horizontalTexture, batch, horizontal));
        tileViewList.add(new TileView(world, leftTile, verticalTexture, batch, vertical));
        tileViewList.add(new TileView(world, rightTile, verticalTexture, batch, vertical));

    }

    @Override
    public void onCreate() {
        for (TileView tileView : tileViewList)
            tileView.createBody();
    }

    @Override
    public void onRender() {
        for (TileView tileView : tileViewList)
            tileView.renderBody();
    }

    public ArrayList<Body> getWallList() {
        ArrayList<Body> tmp = new ArrayList<Body>();
        for (int i = 0; i < tileViewList.size(); i++)
            tmp.add(tileViewList.get(i).getBody());
        return tmp;
    }
}
