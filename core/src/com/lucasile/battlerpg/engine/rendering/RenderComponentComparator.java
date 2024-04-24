package com.lucasile.battlerpg.engine.rendering;

import com.lucasile.battlerpg.engine.ecs.component.components.RenderComponent;

import java.util.Comparator;

public class RenderComponentComparator implements Comparator<RenderComponent> {

    @Override
    public int compare(RenderComponent o1, RenderComponent o2) {
        //sort in descending order
        return Float.compare(o1.getTransform().getPosition().z, o2.getTransform().getPosition().z);
    }

}

