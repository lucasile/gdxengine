package com.lucasile.battlerpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.lucasile.battlerpg.engine.Engine;
import com.lucasile.battlerpg.engine.camera.CameraWrapper;
import com.lucasile.battlerpg.engine.ecs.component.components.renderable.TransformComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.renderable.UIComponent;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;
import com.lucasile.battlerpg.engine.ecs.entity.EntityManager;
import com.lucasile.battlerpg.engine.ecs.entity.builder.builders.TexturedEntityBuilder;
import com.lucasile.battlerpg.engine.ecs.entity.builder.builders.UIEntityBuilder;
import com.lucasile.battlerpg.engine.main.Main;
import com.lucasile.battlerpg.engine.math.VectorUtils;
import com.lucasile.battlerpg.engine.settings.GameSettings;

public class RPGGame implements Main {

    private static RPGGame instance;

    private Engine engine;

    private Entity entity;
    private TransformComponent transform;

    private float moveSpeed;

    private boolean firstLoop;

    private ScreenViewport uiViewport;

    public RPGGame() {
        instance = this;
        engine = new Engine(this);
        firstLoop = false;
        moveSpeed = 1000;
    }

    public void update(float delta, CameraWrapper camera) {

        if (!firstLoop) {

            uiViewport = engine.getGameScreen().getScreenViewport();

            Entity ui = new UIEntityBuilder(uiViewport.getCamera())
                    .setPosition(new Vector3(0, 0, -10))
                    .build();

            EntityManager.addEntity(ui);

            EntityManager.addEntity(
                    new TexturedEntityBuilder()
                            .setTexture("sky.jpeg")
                            .setPosition(new Vector3(100, 100, -1))
                            .setDimensions(new Vector2(GameSettings.CAMERA_WIDTH, GameSettings.CAMERA_HEIGHT))
                            .build()
            );

            entity = new TexturedEntityBuilder()
                    .setPosition(new Vector3(0, 500, 0))
                    .build();

            transform = (TransformComponent) entity.getComponent("TransformComponent");

            EntityManager.addEntity(entity);

            EntityManager.addEntity(
                    new TexturedEntityBuilder()
                            .setPosition(new Vector3(50, 50, -2))
                            .build()
            );

            firstLoop = true;
            camera.bind(entity);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            transform.getPosition().add(0, moveSpeed * delta, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            transform.getPosition().add(-moveSpeed * delta, 0, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            transform.getPosition().add(0, -moveSpeed * delta, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            transform.getPosition().add(moveSpeed * delta, 0, 0);
        }

    }



    public Engine getEngine() {
        return engine;
    }

    public static RPGGame getInstance() {
        return instance;
    }
}
