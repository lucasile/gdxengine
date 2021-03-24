package com.lucasile.battlerpg.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lucasile.battlerpg.engine.camera.CameraWrapper;
import com.lucasile.battlerpg.engine.ecs.component.Component;
import com.lucasile.battlerpg.engine.ecs.entity.EntityManager;
import com.lucasile.battlerpg.engine.rendering.Renderer;
import com.lucasile.battlerpg.engine.settings.GameSettings;
import com.lucasile.battlerpg.game.RPGGame;

public class GameScreen implements Screen {

    private RPGGame rpgGame;

    private SpriteBatch batch;
    private CameraWrapper camera;
    private Renderer renderer;

    public GameScreen(SpriteBatch batch) {
        rpgGame = RPGGame.getInstance();
        this.batch = batch;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.setProjectionMatrix(camera.combined);

        camera.update();

        EntityManager.updateEntities();
        rpgGame.update(delta, camera);

        batch.begin();
        renderer.render();
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        EntityManager.getEntities().forEach(entity -> entity.getComponents().forEach(Component::dispose));
    }

    @Override
    public void show() {
        renderer = new Renderer(batch);
        camera = new CameraWrapper(GameSettings.CAMERA_WIDTH, GameSettings.CAMERA_HEIGHT, GameSettings.CAMERA_Z_INDEX);
        camera.position.set((float) GameSettings.CAMERA_WIDTH / 2, (float) GameSettings.CAMERA_HEIGHT / 2, GameSettings.CAMERA_Z_INDEX);
    }

    public CameraWrapper getCamera() {
        return camera;
    }
}
