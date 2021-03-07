package com.lucasile.battlerpg.engine.ecs.entity;

import com.lucasile.battlerpg.engine.ecs.component.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Entity {

    private List<Component> components;

    public Entity() {
        init();
    }

    public Entity(Component... components) {
        init();
        Collections.addAll(this.components, components);
    }

    private void init() {
        components = new ArrayList<>();
        onCreate();
    }

    protected abstract void update();
    protected abstract void onCreate();
    protected abstract void onDestroy();

    public void updateEntity() {
        update();
        updateComponents();
    }

    //CREATE IS CALLED WHEN ENTITY IS ADDED TO THE ENTITY MANAGER
    public void create() {
        onCreate();
    }

    //DESTROY IS CALLED WHEN ENTITY IS REMOVED FROM THE ENTITY MANAGER
    public void destroy() {
        onDestroy();
    }

    private void updateComponents() {
        components.forEach(component -> {
            if (component.isActive())
                component.update();
        });
    }

    public void addComponent(Component component) {
        components.add(component);
        activateComponent(component);
    }

    public void addComponents(Component... components) {
        Arrays.stream(components).forEach(this::addComponent);
    }

    public void activateComponent(Component component) {
        component.setActive(true);
        component.onActivate();
    }

    public void deactivateComponent(Component component) {
        component.setActive(false);
        component.onDeactivate();
    }

    public Component getComponent(String componentString) {
        return components.stream().filter(component -> component.getName().equals(componentString)).findFirst().orElse(null);
    }

}