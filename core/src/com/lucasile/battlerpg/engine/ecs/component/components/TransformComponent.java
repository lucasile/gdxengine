package com.lucasile.battlerpg.engine.ecs.component.components;

import com.badlogic.gdx.math.Vector2;
import com.lucasile.battlerpg.engine.ecs.component.Component;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;

public class TransformComponent extends Component {

    private Vector2 position, dimensions;

    public TransformComponent(Entity entity, Vector2 position, Vector2 dimensions) {
        super(entity, "TransformComponent");
        this.position = position;
        this.dimensions = dimensions;
    }

    public TransformComponent(Entity entity) {
        super(entity, "TransformComponent");
        this.position = Vector2.Zero;
        this.dimensions = Vector2.Zero;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getDimensions() {
        return dimensions;
    }

    public void setDimensions(Vector2 dimensions) {
        this.dimensions = dimensions;
    }
}
