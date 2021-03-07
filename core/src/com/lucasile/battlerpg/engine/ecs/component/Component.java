package com.lucasile.battlerpg.engine.ecs.component;

import com.lucasile.battlerpg.engine.ecs.entity.Entity;

public abstract class Component {

    private Entity entity;

    private String name;
    private boolean active;

    public Component(Entity entity, String name) {
        this.entity = entity;
        this.name = name;
        this.active = true;
    }

    /*
    Override following methods to be able to implement them
    in subclasses.
     */

    public void update() {}

    public void onActivate() {}

    public void onDeactivate() {}

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    protected Entity getEntity() {
        return entity;
    }

    public String getName() {
        return name;
    }
}
