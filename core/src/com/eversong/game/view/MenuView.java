package com.eversong.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eversong.game.controller.ClickBallController;
import com.eversong.game.controller.CloudController;
import com.eversong.game.controller.Eversong;
import com.eversong.game.model.Player;

/**
 * Created by jacobth on 2015-08-13.
 */
public class MenuView {

    private final SpriteBatch batch;
    private final Sprite background;
    private final Stage stage;
    private final OrthographicCamera camera;
  //  private final static int[] SCREEN_RESOLUTION = {Gdx.graphics.getWidth(), Gdx.graphics.getHeight()};
    private final static float DEFAULT_ALPHA = 1f;
    private BitmapFont font;
    private BitmapFont titleFont;
    private ClickBallController clickBallController;
    private Player player;
    private World world;
    private GlyphLayout layout;
    private GlyphLayout layout2;

    private CloudController cloudController;
    private CloudController cloudController2;

    private ImageButton playButton;
    private ImageButton soundButton;
    private ImageButton muteButton;

    public MenuView() {
        stage = new Stage();
        camera = new OrthographicCamera(540, 960);
        stage.getViewport().setCamera(camera);
        batch = new SpriteBatch();
        layout = new GlyphLayout();
        layout2 = new GlyphLayout();
       // font = new BitmapFont(Gdx.files.internal("test.fnt"));
     //   FileHandle backFileHandle = Gdx.files.internal("background.png");

        font = new BitmapFont(Gdx.files.internal("test.fnt"));
        titleFont = new BitmapFont(Gdx.files.internal("test.fnt"));
        FileHandle backFileHandle = Gdx.files.internal("background.png");

        Texture backgroundTexture = new Texture(backFileHandle);
        background = new Sprite(backgroundTexture);
        background.setSize(camera.viewportWidth, camera.viewportHeight);
        world = new World(new Vector2(0, 0), true);
        createPlay();
        createCloud();
    }

    public void createPlay() {
      //  final Drawable playDrawable = new TextureRegionDrawable(new TextureRegion(
          //      new Texture(Gdx.files.internal("play.png"))));

        final Drawable playDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("play.png"))));
        playButton = new ImageButton(playDrawable);
        playButton.setPosition(-playButton.getWidth() / 2, 0);
        playButton.setBounds(playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        stage.addActor(playButton);

        final Drawable soundDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("sound.png"))));
        soundButton = new ImageButton(soundDrawable);
        soundButton.setPosition(-camera.viewportWidth / 2 + soundButton.getWidth() / 2, camera.viewportHeight / 2 - soundButton.getHeight() * 2);
        soundButton.setBounds(soundButton.getX(), soundButton.getY(), soundButton.getWidth(), soundButton.getHeight());
        stage.addActor(soundButton);

        final Drawable muteDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("mute.png"))));
        muteButton = new ImageButton(muteDrawable);
        muteButton.setSize(0, 0);
        muteButton.setPosition(-camera.viewportWidth / 2 + soundButton.getWidth() / 2, camera.viewportHeight / 2 - soundButton.getHeight() * 2);
        muteButton.setBounds(muteButton.getX(), muteButton.getY(), muteButton.getWidth(), muteButton.getHeight());
        stage.addActor(muteButton);
    }

    public Stage getStage(){
        return stage;
    }

    public ImageButton getPlayButton(){
        return playButton;
    }
    public ImageButton getSoundButton(){
        return soundButton;
    }
    public ImageButton getMuteButton(){
        return muteButton;
    }

    public void update() {
        world.step(1f / 60f, 6, 2);

        batch.begin();
        camera.update();
        batch.draw(background, -camera.viewportWidth / 2, -camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight);
        drawClouds();
        draw();
        for(Actor a: stage.getActors()){
            a.draw(batch, DEFAULT_ALPHA);
        }
        batch.setProjectionMatrix(camera.combined);
        batch.end();
    }

    public void draw() {
        layout.setText(font, Eversong.highScore + "");
        float width = layout.width;
        font.draw(batch, layout, 0 - width / 2, -playButton.getHeight() / 2);

        layout2.setText(font, "Dropbird");
        float width2 = layout2.width;
        titleFont.draw(batch, layout2, 0 - width2 / 2, playButton.getHeight()*3);
    }

    public void createClickBall() {
        player = new Player();
        clickBallController = new ClickBallController(player, batch, world, camera);
        player.getClickBall().setRadius(clickBallController.getSprite().getHeight() / 2);
        clickBallController.getBody().applyForceToCenter(-10f, -24f, true);
    }
    private void createCloud() {
        cloudController = new CloudController(camera, batch, -camera.viewportWidth/2);
        cloudController2 = new CloudController(camera, batch, -camera.viewportWidth/9);
    }
    private void drawClouds(){
        cloudController.draw();
        cloudController2.draw();
    }

    public Camera getCamera() {
        return camera;
    }
    public SpriteBatch getBatch() {
        return batch;
    }
    public World getWorld(){
        return world;
    }
}
