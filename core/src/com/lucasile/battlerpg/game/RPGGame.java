package com.lucasile.battlerpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.Engine;
import com.lucasile.battlerpg.engine.camera.CameraWrapper;
import com.lucasile.battlerpg.engine.ecs.component.components.RenderComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.TransformComponent;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;
import com.lucasile.battlerpg.engine.ecs.entity.EntityManager;
import com.lucasile.battlerpg.engine.settings.GameSettings;

public class RPGGame {

    private static RPGGame instance;

    private Engine engine;

    private TestEntity entity;
    private TransformComponent transform;
    private Vector2 position;

    private float moveSpeed;

    private boolean firstLoop;

    public RPGGame() {
        instance = this;
        engine = new Engine();
        firstLoop = false;
        moveSpeed = 1000;
    }

    public void update(float delta, CameraWrapper camera) {

        if (!firstLoop) {
            EntityManager.addEntity(new BackgroundTest());
            entity = new TestEntity(new Vector3(0, 0, 0));
            transform = (TransformComponent) entity.getComponent("TransformComponent");
            EntityManager.addEntity(entity);
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
        return new Engine();
    }

    public static RPGGame getInstance() {
        return instance;
    }
}
