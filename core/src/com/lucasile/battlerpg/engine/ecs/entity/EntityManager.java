package com.lucasile.battlerpg.engine.ecs.entity;

import java.util.HashSet;
import java.util.Set;

public class EntityManager {

    private static Set<Entity> entities = new HashSet<>();

    public static void updateEntities() {
        for (Entity entity : entities) {
            entity.updateEntity();
        }
    }

    public static void addEntity(Entity entity) {
        entity.create();
        entities.add(entity);
    }

    public static void destroyEntity(Entity entity) {
        entity.destroy();
        entities.remove(entity);
    }

    public static Set<Entity> getEntities() {
        return entities;
    }
}
