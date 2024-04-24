package com.lucasile.battlerpg.engine.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.ecs.component.components.RenderComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.TransformComponent;
import com.lucasile.battlerpg.engine.rendering.groups.RenderGroup;

import java.util.SortedSet;
import java.util.TreeSet;

public class Renderer {

    private static Renderer instance;

    private final SortedSet<RenderGroup> uiRenderGroups;
    private final SortedSet<RenderGroup> worldRenderGroups;

    public Renderer() {

        if (instance == null) {
            instance = this;
        } else {
            System.out.println("Cannot make more than one singleton instance (Renderer)");
        }

        uiRenderGroups = new TreeSet<>(new RenderGroupComparator());
        worldRenderGroups = new TreeSet<>(new RenderGroupComparator());

        for (BatchType batchType : BatchType.values()) {
            RenderGroup renderGroup = new RenderGroup(batchType.getId());
            if (batchType == BatchType.UI) {
                uiRenderGroups.add(renderGroup);
            } else {
                worldRenderGroups.add(renderGroup);
            }
        }
    }

    public void renderUI(SpriteBatch batch) {
        render(batch, uiRenderGroups);
    }

    public void renderWorld(SpriteBatch batch) {
        render(batch, worldRenderGroups);
    }

    private void render(SpriteBatch batch, SortedSet<RenderGroup> renderGroups) {
        for (RenderGroup renderGroup : renderGroups) {

            for (RenderComponent renderComponent : renderGroup.getActiveComponents()) {

                TransformComponent transform = renderComponent.getTransform();
                Vector3 position = transform.getPosition();
                Vector2 dimensions = transform.getDimensions() ;

                batch.draw(renderComponent.getSprite(), position.x, position.y, dimensions.x, dimensions.y);
            }
        }
    }

    public void addUIRenderGroup(RenderGroup renderGroup) {
        uiRenderGroups.add(renderGroup);
    }

    public void addWorldRenderGroup(RenderGroup renderGroup) {
        worldRenderGroups.add(renderGroup);
    }

    public RenderGroup getWorldRenderGroup(int id) {
        for (RenderGroup renderGroup : worldRenderGroups) {
            if (renderGroup.getId() == id) {
                return renderGroup;
            }
        }
        return null;
    }

    public RenderGroup getUIRenderGroup(int id) {
        for (RenderGroup renderGroup : uiRenderGroups) {
            if (renderGroup.getId() == id) {
                return renderGroup;
            }
        }
        return null;
    }

    public SortedSet<RenderGroup> getUIRenderGroups() {
        return uiRenderGroups;
    }

    public SortedSet<RenderGroup> getWorldRenderGroups() {
        return worldRenderGroups;
    }

    public static Renderer getInstance() {
        return instance;
    }
}
