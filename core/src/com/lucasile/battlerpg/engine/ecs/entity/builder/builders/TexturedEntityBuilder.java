package com.lucasile.battlerpg.engine.ecs.entity.builder.builders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.ecs.component.components.renderable.RenderComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.renderable.TransformComponent;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;
import com.lucasile.battlerpg.engine.ecs.entity.builder.EntityBuilder;
import com.lucasile.battlerpg.engine.rendering.groups.RenderGroup;

public class TexturedEntityBuilder extends EntityBuilder {

    protected Vector3 position;
    protected Vector2 dimensions;
    protected Texture texture;
    private RenderGroup renderGroup;

    public TexturedEntityBuilder setPosition(Vector3 position) {
        this.position = position;
        return this;
    }

    public TexturedEntityBuilder setDimensions(Vector2 dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public TexturedEntityBuilder setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    public TexturedEntityBuilder setTexture(String path) {
        this.texture = new Texture(path);
        return this;
    }

    public TexturedEntityBuilder setRenderGroup(RenderGroup renderGroup) {
        this.renderGroup  = renderGroup;
        return this;
    }

    protected RenderComponent createRenderComponent(Vector3 position, Vector2 dimensions) {
        if (renderGroup == null)
            return new RenderComponent(position, dimensions, texture);
        return new RenderComponent(position, dimensions, texture, renderGroup);
    }

    @Override
    public Entity build() {

        if (texture == null)
            texture = new Texture("badlogic.jpg"); //TODO: Change this to some default square

        if (position == null)
            position = new Vector3(0, 0, 0);

        if (dimensions == null)
            dimensions = new Vector2(texture.getWidth(), texture.getHeight());

        RenderComponent renderComponent = createRenderComponent(position, dimensions);

        setComponents(renderComponent);

        return super.build();
    }

}
