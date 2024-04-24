package com.lucasile.battlerpg.engine.rendering;

public enum BatchType {

    WORLD(0),
    UI(Integer.MIN_VALUE);

    private int id;

    BatchType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
