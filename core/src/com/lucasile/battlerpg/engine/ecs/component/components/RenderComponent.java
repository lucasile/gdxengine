package com.lucasile.battlerpg.engine.ecs.component.components;

import com.badlogic.gdx.graphics.Texture;
import com.lucasile.battlerpg.engine.ecs.component.Component;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;
import com.lucasile.battlerpg.engine.rendering.BatchType;
import com.lucasile.battlerpg.engine.rendering.Renderer;
import com.lucasile.battlerpg.engine.rendering.groups.RenderGroup;

public class RenderComponent extends Component {

    protected Renderer renderer;

    private TransformComponent transform;
    private Texture sprite;

    private RenderGroup renderGroup;

    /**
     * Creates RenderComponent component. If last argument, renderGroup is not specified, will choose the first
     * available renderGroup. If that renderGroup doesn't exist, creates it. This may be slow at runtime if there are many render
     * components being constructed.
     * @param sprite
     */
    public RenderComponent(TransformComponent transform, Texture sprite) {

        super("RenderComponent");
        renderer = Renderer.getInstance();
        this.sprite = sprite;

        if (renderer.getWorldRenderGroups().isEmpty()) {
            renderGroup = new RenderGroup(0);
        } else {
            renderGroup = renderer.getWorldRenderGroups().first();
        }

        addRenderGroup(renderGroup);

        this.transform = transform;
    }

    public RenderComponent(TransformComponent transform, Texture sprite, RenderGroup renderGroup) {

        super("RenderComponent");
        renderer = Renderer.getInstance();
        this.sprite = sprite;

        this.renderGroup = renderGroup;

        addRenderGroup(renderGroup);

        this.transform = transform;
    }

    protected void initRenderGroup() {
        renderGroup.addToRenderGroup(this);
    }

    protected void initActiveGroup() {
        renderGroup.addActiveGroup(this);
    }

    protected void deleteActiveGroup() {
        renderGroup.removeActiveGroup(this);
    }

    protected void addRenderGroup(RenderGroup renderGroup) {
        renderer.addWorldRenderGroup(renderGroup);
    }

    @Override
    public void setActive(boolean active) {

        if (isActive() == active)
            return;

        if (active)
            initActiveGroup();
        else
            deleteActiveGroup();

        super.setActive(active);
    }

    @Override
    public void onActivate() {
        initActiveGroup();
        initRenderGroup();
    }

    @Override
    public void dispose() {
        sprite.dispose();
        renderGroup.removeFromRenderGroup(this);
        renderGroup.removeActiveGroup(this);
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

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

}
