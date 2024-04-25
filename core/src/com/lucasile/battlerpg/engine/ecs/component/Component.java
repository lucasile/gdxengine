package com.lucasile.battlerpg.engine.ecs.component;

public class Component {

    private String name;
    private boolean active;

    public Component(String name) {
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

    public void dispose() {}

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }
}
