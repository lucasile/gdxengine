package com.lucasile.battlerpg.engine.ecs.entity;

import com.lucasile.battlerpg.engine.ecs.component.Component;
import com.lucasile.battlerpg.engine.ecs.component.components.TransformComponent;

import javax.xml.crypto.dsig.Transform;
import java.util.*;

public class Entity {

    private List<Component> components;

    // used for O(1) lookup when having string. Useful once we have many components.
    private Map<String, Component> componentMap;

    private TransformComponent transform;

    public Entity(Component... components) {
        this.components = new ArrayList<>();
        componentMap = new HashMap<>();
        onCreate();
        addComponents(components);
    }

    protected void update() {}
    protected void onCreate() {};
    protected void onDestroy() {};

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
        disposeComponents();
        onDestroy();
    }

    public void disposeComponents() {
        components.forEach(Component::dispose);
    }

    private void updateComponents() {
        components.forEach(component -> {
            if (component.isActive())
                component.update();
        });
    }

    public void addComponent(Component component) {
        if (component instanceof TransformComponent) {
            transform = (TransformComponent) component;
        }
        components.add(component);
        componentMap.put(component.getName(), component);
        activateComponent(component);
    }

    public void removeComponent(Component component) {
        if (component instanceof TransformComponent) {
            transform = null;
        }
        components.remove(component);
        componentMap.remove(component.getName());
        deactivateComponent(component);
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
        return componentMap.getOrDefault(componentString, null);
    }

    public List<Component> getComponents() {
        return components;
    }

    public TransformComponent getTransform() {
        return transform;
    }

}
