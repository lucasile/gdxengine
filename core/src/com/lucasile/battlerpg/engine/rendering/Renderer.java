package com.lucasile.battlerpg.engine.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.ecs.component.components.RenderComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.TransformComponent;
import com.lucasile.battlerpg.engine.rendering.groups.RenderGroup;
import com.lucasile.battlerpg.engine.settings.GameSettings;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Renderer {

    private static Renderer instance;

    private final SortedSet<RenderGroup> renderGroups;
    private final SpriteBatch batch;

    public Renderer(SpriteBatch batch) {

        if (instance == null) {
            instance = this;
        } else {
            System.out.println("Cannot make more than one singleton instance (Renderer)");
        }

        renderGroups = new TreeSet<>(new RenderGroupComparator());
        this.batch = batch;
    }

    public void render() {
        for (RenderGroup renderGroup : renderGroups) {
            for (RenderComponent renderComponent : renderGroup.getRenderComponents()) {
                if (renderComponent.isRenderingSprite()) {
                    TransformComponent transform = renderComponent.getTransform();
                    Vector3 position = transform.getPosition();
                    Vector2 dimensions = transform.getDimensions() ;
                    batch.draw(renderComponent.getSprite(), position.x, position.y, dimensions.x, dimensions.y);
                }
            }
        }
    }

    public void addRenderGroup(RenderGroup renderGroup) {
        renderGroups.add(renderGroup);
        sortRenderGroup();
    }

    private void sortRenderGroup() {
        for (RenderGroup renderGroup : renderGroups) {
            sortRenderComponents(renderGroup.getRenderComponents());
        }
    }

    public void sortRenderComponents(List<RenderComponent> renderComponents) {
        // insertion sort
        for (int i = 1; i < renderComponents.size(); i++) {

            RenderComponent iComponent = renderComponents.get(i);

            int j = i - 1;

            while (j >= 0 && renderComponents.get(j).getTransform().getPosition().z > iComponent.getTransform().getPosition().z) {
                renderComponents.set(j + 1, renderComponents.get(j));
                j--;
            }

            renderComponents.set(j + 1, iComponent);

        } 
    }

    public RenderGroup getRenderGroup(int id) {
        for (RenderGroup renderGroup : renderGroups) {
            if (renderGroup.getId() == id) {
                return renderGroup;
            }
        }
        return null;
    }

    public SortedSet<RenderGroup> getRenderGroups() {
        return renderGroups;
    }

    public static Renderer getInstance() {
        return instance;
    }
}
