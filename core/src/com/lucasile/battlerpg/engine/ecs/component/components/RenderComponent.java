package com.lucasile.battlerpg.engine.ecs.component.components;

import com.badlogic.gdx.graphics.Texture;
import com.lucasile.battlerpg.engine.ecs.component.Component;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;
import com.lucasile.battlerpg.engine.rendering.Renderer;
import com.lucasile.battlerpg.engine.rendering.groups.RenderGroup;

public class RenderComponent extends Component {

    private Renderer renderer;

    private TransformComponent transform;
    private Texture sprite;
    private boolean renderingSprite = true;
    private RenderGroup renderGroup;

    /**
     * Creates RenderComponent component. If last argument, renderGroup is not specified, will choose the first
     * available renderGroup. If that renderGroup doesn't exist, creates it. This may be slow at runtime if there are many render
     * components being constructed.
     * @param entity
     * @param sprite
     */
    public RenderComponent(Entity entity, Texture sprite) {

        super(entity, "RenderComponent");
        renderer = Renderer.getInstance();
        this.sprite = sprite;

        if (renderer.getRenderGroups().isEmpty()) {
            this.renderGroup = new RenderGroup(0);
        } else {
            this.renderGroup = renderer.getRenderGroups().first();
        }

    }

    public RenderComponent(Entity entity, Texture sprite, RenderGroup renderGroup) {

        super(entity, "RenderComponent");
        renderer = Renderer.getInstance();
        this.sprite = sprite;

        this.renderGroup = renderGroup;
    }

    private void initRenderGroup() {
        renderGroup.addToRenderGroup(this);
        addRenderGroup();
    }

    private void addRenderGroup() {
        renderer.addRenderGroup(renderGroup);
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

        initRenderGroup();

    }

    @Override
    public void dispose() {
        sprite.dispose();
        renderGroup.removeFromRenderGroup(this);
    }

    public RenderGroup getRenderGroup() {
        return renderGroup;
    }

    public void setRenderGroup(RenderGroup renderGroup) {
        this.renderGroup = renderGroup;
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
