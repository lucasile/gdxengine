package com.lucasile.battlerpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.lucasile.battlerpg.engine.ecs.component.components.RenderComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.TransformComponent;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;

public class TestEntity extends Entity {

    public TestEntity(Vector2 position) {
        Texture texture = new Texture("badlogic.jpg");
        TransformComponent transform = new TransformComponent(this, position, new Vector2(texture.getWidth(), texture.getHeight()));
        RenderComponent renderComponent = new RenderComponent(this, texture);
        addComponents(transform, renderComponent);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void onCreate() {
    }

    @Override
    protected void onDestroy() {

    }

}
