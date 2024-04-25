package com.lucasile.battlerpg.engine.ecs.component.components.renderable;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.math.VectorUtils;
import com.lucasile.battlerpg.engine.rendering.BatchType;
import com.lucasile.battlerpg.engine.rendering.Renderer;
import com.lucasile.battlerpg.engine.rendering.groups.RenderGroup;

public class UIComponent extends RenderComponent {

    // Don't store in vector since I want to manage the updating of these values as an event
    private float x;
    private float y;
    private float z;

    private Camera camera;

    public UIComponent(Vector3 position, Vector2 dimensions, Texture sprite, Camera camera) {

        // Similarly with RenderComponent, UIComponent can be accessed by casting from "TransformComponent"
        super(
                VectorUtils.relativeVector(position.x, position.y, position.z, camera),
                dimensions,
                sprite,
                Renderer.getInstance().getUIRenderGroup(BatchType.UI.getId()));

        this.camera = camera;

        x = position.x;
        y = position.y;
        z = position.z;
    }

    @Override
    protected void addRenderGroup(RenderGroup renderGroup) {
        addRenderGroupTyped(renderGroup, BatchType.UI);
    }

    public Vector3 getSimplePosition() {
        return new Vector3(x, y, z);
    }

    public void setSimplePosition(Vector3 position) {
        x = position.x;
        y = position.y;
        z = position.z;
        updateRelativePosition();
    }

    public void setSimplePosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        updateRelativePosition();
    }

    public void updateRelativePosition() {
        setPosition(getRelativePosition());
    }

    private Vector3 getRelativePosition() {
        return VectorUtils.relativeVector(x, y, z, camera);
    }


}
