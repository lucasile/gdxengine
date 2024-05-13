package com.lucasile.battlerpg.engine.rendering;

import com.lucasile.battlerpg.engine.rendering.groups.RenderGroup;

import java.util.Comparator;

public class RenderGroupComparator implements Comparator<RenderGroup> {

    @Override
    public int compare(RenderGroup o1, RenderGroup o2) {
        //sort in ascending order
        return o1.getId() - o2.getId();
    }

}
