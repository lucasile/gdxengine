package com.lucasile.battlerpg.engine.ecs.entity.builder;

import com.lucasile.battlerpg.engine.ecs.component.Component;
import com.lucasile.battlerpg.engine.ecs.entity.Entity;

public class EntityBuilder {

    private Component[] components;

    public EntityBuilder() {
        this.components = new Component[0];
    }

    protected void setComponents(Component... components) {
        this.components = components;
    }

    public Entity build() {
        return new Entity(components);
    }
}