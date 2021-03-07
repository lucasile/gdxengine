package com.lucasile.battlerpg.engine.ecs.entity;

import java.util.HashSet;
import java.util.Set;

public class EntityManager {

    private static EntityManager instance;

    private Set<Entity> entities;

    public EntityManager() {
        instance = this;
        entities = new HashSet<>();
    }

    public void updateEntities() {
        entities.forEach(Entity::updateEntity);
    }

    public void addEntity(Entity entity) {
        entity.create();
        entities.add(entity);
    }

    public void destroyEntity(Entity entity) {
        entity.destroy();
        entities.remove(entity);
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public static EntityManager getInstance() {
        return instance;
    }
}
