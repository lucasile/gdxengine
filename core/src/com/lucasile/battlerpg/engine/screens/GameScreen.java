package com.lucasile.battlerpg.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lucasile.battlerpg.engine.ecs.entity.EntityManager;
import com.lucasile.battlerpg.engine.rendering.Renderer;
import com.lucasile.battlerpg.engine.settings.GameSettings;
import com.lucasile.battlerpg.game.RPGGame;

public class GameScreen implements Screen {

    private RPGGame rpgGame;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Renderer renderer;
    private EntityManager entityManager;

    public GameScreen(SpriteBatch batch) {
        rpgGame = RPGGame.getInstance();
        entityManager = new EntityManager();
        this.batch = batch;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        camera.update();

        rpgGame.update();

        batch.begin();
        renderer.render();
        batch.end();

        entityManager.updateEntities();

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
    }

    @Override
    public void show() {
        renderer = new Renderer(batch);
        entityManager = new EntityManager();
        camera = new OrthographicCamera(GameSettings.CAMERA_WIDTH, GameSettings.CAMERA_HEIGHT);
        camera.position.set((float) GameSettings.CAMERA_WIDTH / 2, (float) GameSettings.CAMERA_HEIGHT / 2, GameSettings.CAMERA_Z_INDEX);
    }

}
