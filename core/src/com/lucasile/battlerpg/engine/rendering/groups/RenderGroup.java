package com.lucasile.battlerpg.engine.rendering.groups;

import com.lucasile.battlerpg.engine.ecs.component.components.RenderComponent;

import java.util.ArrayList;
import java.util.List;

public class RenderGroup {

    private int id;
    private final List<RenderComponent> renderComponents;

    public RenderGroup(int id) {
        this.id = id;
        renderComponents = new ArrayList<>();
    }

    public void addToRenderGroup(RenderComponent component) {
        renderComponents.add(component);
    }

    public void removeFromRenderGroup(RenderComponent component) {
        renderComponents.remove(component);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RenderComponent> getRenderComponents() {
        return renderComponents;
    }

}
