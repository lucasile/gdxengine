package com.lucasile.battlerpg.engine.rendering;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.lucasile.battlerpg.engine.ecs.component.components.renderable.RenderComponent;
import com.lucasile.battlerpg.engine.math.VectorUtils;
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

            getRenderGroupsFromType(batchType).add(renderGroup);

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

                Vector3 position = renderComponent.getPosition();
                Vector2 dimensions = renderComponent.getDimensions() ;

                batch.draw(renderComponent.getSprite(), position.x, position.y, dimensions.x, dimensions.y);
            }
        }
    }

    public void addRenderGroup(RenderGroup renderGroup, BatchType batchType) {

        SortedSet<RenderGroup> group = getRenderGroupsFromType(batchType);

        if (group == null)
            return;

        group.add(renderGroup);
    }

    private SortedSet<RenderGroup> getRenderGroupsFromType(BatchType batchType) {

        switch (batchType) {

            case UI:
                return uiRenderGroups;

            case WORLD:
                return worldRenderGroups;

            default:
                return null;

        }

    }

    private void addUIRenderGroup(RenderGroup renderGroup) {
        uiRenderGroups.add(renderGroup);
    }

    private void addWorldRenderGroup(RenderGroup renderGroup) {
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
