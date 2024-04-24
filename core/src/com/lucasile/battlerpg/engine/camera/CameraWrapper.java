package com.lucasile.battlerpg.engine.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;

public class CameraWrapper extends OrthographicCamera {

    private Entity boundChild;
    private boolean bound;
    private int width, height, zIndex;

    public CameraWrapper(int width, int height, int zIndex) {
        super(width, height);
        this.width = width;
        this.height = height;
        this.zIndex = zIndex;
        bound = false;
    }

    @Override
    public void update() {
        // Overriding default update method in OrthographicCamera and calling the updateFrustum method. Preserves functionality, can inject code.
        update(true);
        bindUpdate();
    }

    private void bindUpdate() {

        if (!bound) {
            return;
        }

        Vector3 childPosition = boundChild.getTransform().getPosition();
        Vector2 dimensions = boundChild.getTransform().getDimensions();

        position.set(childPosition.x + dimensions.x / 2, childPosition.y + dimensions.y / 2, zIndex);

    }

    //This will bind to a new entity
    public void bind(Entity entity) {

        //Checks to see if entity has default transform provided
        if (entity.getTransform() == null) {
            System.out.println("Cannot bind an entity to the camera that does not provide a transform component.");
            return;
        }

        boundChild = entity;

        bound = true;

    }

    //This will bind to the preexisting referenced entity.
    public void bind() {
        if (boundChild != null) {
            bound = true;
        }
    }

    //This will unbind the entity child to the camera. Note that this does not destroy the reference and can easily be bound back by calling bind()
    public void unbind() {
        bound = false;
    }

    public Entity getBoundChild() {
        return boundChild;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }

}
