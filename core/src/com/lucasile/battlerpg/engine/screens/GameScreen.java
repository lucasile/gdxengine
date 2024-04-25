package com.lucasile.battlerpg.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.lucasile.battlerpg.engine.camera.CameraWrapper;
import com.lucasile.battlerpg.engine.ecs.component.Component;
import com.lucasile.battlerpg.engine.ecs.component.components.renderable.TransformComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.renderable.UIComponent;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;
import com.lucasile.battlerpg.engine.ecs.entity.EntityManager;
import com.lucasile.battlerpg.engine.main.Main;
import com.lucasile.battlerpg.engine.rendering.Renderer;
import com.lucasile.battlerpg.engine.settings.GameSettings;

public class GameScreen implements Screen {

    private SpriteBatch worldBatch;

    private SpriteBatch uiBatch;
    private Matrix4 screenMatrix;

    private CameraWrapper camera;
    private Renderer renderer;
    private Main main;

    private ScreenViewport screenViewport;
    private ExtendViewport extendViewport; // make this modifiable through the game somehow later

    private int width;
    private int height;

    public GameScreen(Main main, SpriteBatch worldBatch, SpriteBatch uiBatch) {

        this.main = main;
        this.worldBatch = worldBatch;
        this.uiBatch = uiBatch;

        width = 0;
        height = 0;

        screenViewport = new ScreenViewport();

        extendViewport = new ExtendViewport(GameSettings.CAMERA_WIDTH * 2, GameSettings.CAMERA_HEIGHT * 2);
        extendViewport.getCamera().position.set((float) GameSettings.CAMERA_WIDTH / 2, (float) GameSettings.CAMERA_HEIGHT / 2, 0);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        screenViewport.apply();
        extendViewport.apply();

        uiBatch.setProjectionMatrix(screenViewport.getCamera().combined);
        worldBatch.setProjectionMatrix(camera.combined);

        camera.update();

        EntityManager.updateEntities();
        main.update(delta, camera);

        worldBatch.begin();
        renderer.renderWorld(worldBatch);
        worldBatch.end();

        uiBatch.begin();
        renderer.renderUI(uiBatch);
        uiBatch.end();

    }

    @Override
    public void resize(int width, int height) {

        extendViewport.update(width, height);
        screenViewport.update(width, height);

        //TODO: Add extensibility to EntityManager to do this
        // updates ui entity positions to scale with screen
        EntityManager.getEntities().forEach(entity -> {
            TransformComponent transform = entity.getTransform();
            if (transform instanceof UIComponent) {
                ((UIComponent) transform).updateRelativePosition();
            }
        });

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        worldBatch.dispose();
        EntityManager.getEntities().forEach(entity -> entity.getComponents().forEach(Component::dispose));
    }

    @Override
    public void show() {
        renderer = new Renderer();
        camera = new CameraWrapper(GameSettings.CAMERA_WIDTH, GameSettings.CAMERA_HEIGHT, GameSettings.CAMERA_Z_INDEX);
        camera.position.set((float) GameSettings.CAMERA_WIDTH / 2, (float) GameSettings.CAMERA_HEIGHT / 2, GameSettings.CAMERA_Z_INDEX);
        extendViewport.setCamera(camera);
    }

    public ScreenViewport getScreenViewport() {
        return screenViewport;
    }

    public CameraWrapper getCamera() {
        return camera;
    }
}
