package com.lucasile.battlerpg.engine.rendering.groups;

import com.lucasile.battlerpg.engine.ecs.component.components.renderable.RenderComponent;
import com.lucasile.battlerpg.engine.rendering.RenderComponentComparator;

import java.util.*;

public class RenderGroup {

    private int id;
    private final Set<RenderComponent> renderComponents;
    private final SortedSet<RenderComponent> activeComponents;

    public RenderGroup(int id) {
        this.id = id;
        renderComponents = new HashSet<>();
        activeComponents = new TreeSet<>(new RenderComponentComparator());
    }

    public void addToRenderGroup(RenderComponent component) {
        renderComponents.add(component);
    }

    public void removeFromRenderGroup(RenderComponent component) {
        renderComponents.remove(component);
    }

    public void addActiveGroup(RenderComponent component) {
        activeComponents.add(component);
    }

    public void removeActiveGroup(RenderComponent component) {
        activeComponents.remove(component);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<RenderComponent> getRenderComponents() {
        return renderComponents;
    }

    public SortedSet<RenderComponent> getActiveComponents() {
        return activeComponents;
    }

}
