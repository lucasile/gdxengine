package com.lucasile.battlerpg.engine.ecs.component.components.renderable;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.ecs.component.Component;

public class TransformComponent extends Component {

    private Vector3 position;
    private Vector2 dimensions;

    public TransformComponent(Vector3 position, Vector2 dimensions) {
        super("TransformComponent");
        this.position = position;
        this.dimensions = dimensions;
    }

    public TransformComponent(String componentName) {
        super(componentName);
        this.position = Vector3.Zero;
        this.dimensions = Vector2.Zero;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector2 getDimensions() {
        return dimensions;
    }

    public void setDimensions(Vector2 dimensions) {
        this.dimensions = dimensions;
    }
}
