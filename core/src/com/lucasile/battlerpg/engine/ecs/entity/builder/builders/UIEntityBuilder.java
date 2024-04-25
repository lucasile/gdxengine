package com.lucasile.battlerpg.engine.ecs.entity.builder.builders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.ecs.component.components.renderable.RenderComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.renderable.UIComponent;

public class UIEntityBuilder extends TexturedEntityBuilder {

    private Camera camera;

    public UIEntityBuilder(Camera camera) {
        this.camera = camera;
    }

    @Override
    protected RenderComponent createRenderComponent(Vector3 position, Vector2 dimensions) {
        return new UIComponent(position, dimensions, texture, camera);
    }

}
