package com.lucasile.battlerpg.engine.ecs.entity.builder.builders;

import com.lucasile.battlerpg.engine.ecs.component.components.RenderComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.TransformComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.UIComponent;

public class UIEntityBuilder extends TexturedEntityBuilder {

    @Override
    protected RenderComponent createRenderComponent(TransformComponent transform) {
        return new UIComponent(transform, texture);
    }

}
