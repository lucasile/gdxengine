package com.lucasile.battlerpg.engine.ecs.component.components.renderable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.rendering.BatchType;
import com.lucasile.battlerpg.engine.rendering.Renderer;
import com.lucasile.battlerpg.engine.rendering.groups.RenderGroup;

public class RenderComponent extends TransformComponent {

    protected Renderer renderer;

    private Texture sprite;

    private RenderGroup renderGroup;

    /**
     * Creates RenderComponent component. If last argument, renderGroup is not specified, will choose the first
     * available renderGroup. If that renderGroup doesn't exist, creates it. This may be slow at runtime if there are many render
     * components being constructed.
     * @param sprite
     */
    public RenderComponent(Vector3 position, Vector2 dimensions, Texture sprite) {

        // NOTE RenderComponent will be inaccessible from using string "RenderComponent" by design
        // Cast TransformComponent to RenderComponent will get RenderComponent
        super(position, dimensions);
        renderer = Renderer.getInstance();
        this.sprite = sprite;

        if (renderer.getWorldRenderGroups().isEmpty()) {
            renderGroup = new RenderGroup(0);
        } else {
            renderGroup = renderer.getWorldRenderGroups().first();
        }

        addRenderGroup(renderGroup);
    }

    public RenderComponent(Vector3 position, Vector2 dimensions, Texture sprite, RenderGroup renderGroup) {

        super(position, dimensions);
        renderer = Renderer.getInstance();
        this.sprite = sprite;

        this.renderGroup = renderGroup;

        addRenderGroup(renderGroup);
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
        addRenderGroupTyped(renderGroup, BatchType.WORLD);
    }

    protected void addRenderGroupTyped(RenderGroup renderGroup, BatchType batchType) {
        renderer.addRenderGroup(renderGroup, batchType);
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

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

}
