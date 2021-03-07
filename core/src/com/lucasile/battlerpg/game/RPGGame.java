package com.lucasile.battlerpg.game;

import com.badlogic.gdx.math.Vector2;
import com.lucasile.battlerpg.engine.Engine;
import com.lucasile.battlerpg.engine.ecs.component.components.TransformComponent;
import com.lucasile.battlerpg.engine.ecs.entity.EntityManager;

public class RPGGame {

    private static RPGGame instance;

    private Engine engine;

    private TestEntity entity;
    private TransformComponent transform;

    private boolean firstLoop;

    public RPGGame() {
        instance = this;
        engine = new Engine();
        firstLoop = false;
    }

    public void update() {

        if (!firstLoop) {
            entity = new TestEntity(new Vector2(0, 0));
            transform = (TransformComponent) entity.getComponent("TransformComponent");
            EntityManager.getInstance().addEntity(entity);
            firstLoop = true;
        }

        transform.getPosition().add(5, 1);

    }

    public Engine getEngine() {
        return new Engine();
    }

    public static RPGGame getInstance() {
        return instance;
    }
}
