package com.lucasile.battlerpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.ecs.component.components.RenderComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.TransformComponent;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;
import com.lucasile.battlerpg.engine.settings.GameSettings;

public class BackgroundTest extends Entity {

    public BackgroundTest() {
        Texture texture = new Texture("sky.jpeg");
        TransformComponent transform = new TransformComponent(this, new Vector3(0, 0, -10), new Vector2(GameSettings.CAMERA_WIDTH, GameSettings.CAMERA_HEIGHT));
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
