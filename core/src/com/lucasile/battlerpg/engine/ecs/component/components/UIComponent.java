package com.lucasile.battlerpg.engine.ecs.component.components;

import com.badlogic.gdx.graphics.Texture;
import com.lucasile.battlerpg.engine.rendering.BatchType;
import com.lucasile.battlerpg.engine.rendering.Renderer;
import com.lucasile.battlerpg.engine.rendering.groups.RenderGroup;

public class UIComponent extends RenderComponent {

    public UIComponent(TransformComponent transform, Texture sprite) {
        super(transform, sprite, Renderer.getInstance().getUIRenderGroup(BatchType.UI.getId()));
    }

    @Override
    protected void addRenderGroup(RenderGroup renderGroup) {
        renderer.addUIRenderGroup(renderGroup);
    }

}
