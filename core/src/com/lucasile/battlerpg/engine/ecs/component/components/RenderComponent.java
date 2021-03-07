package com.lucasile.battlerpg.engine.ecs.component.components;

import com.badlogic.gdx.graphics.Texture;
import com.lucasile.battlerpg.engine.ecs.component.Component;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;
import com.lucasile.battlerpg.engine.rendering.Renderer;

public class RenderComponent extends Component {

    private Renderer renderer;

    private TransformComponent transform;
    private Texture sprite;
    private boolean renderingSprite = true;

    public RenderComponent(Entity entity, Texture sprite) {
        super(entity, "RenderComponent");
        renderer = Renderer.getInstance();
        this.sprite = sprite;
    }

    @Override
    public void update() {
        if (renderingSprite) {
            renderer.addToQueue(this);
        }
    }

    @Override
    public void onActivate() {

        Component rawTransform = getEntity().getComponent("TransformComponent");

        if (rawTransform != null) {
            transform = (TransformComponent) rawTransform;
        } else {
            setActive(false);
            throw new NullPointerException("Transform in render component not found");
        }

    }

    public TransformComponent getTransform() {
        return transform;
    }

    public void setTransform(TransformComponent transform) {
        this.transform = transform;
    }

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    public boolean isRenderingSprite() {
        return renderingSprite;
    }

    public void setRenderingSprite(boolean renderingSprite) {
        this.renderingSprite = renderingSprite;
    }
}
