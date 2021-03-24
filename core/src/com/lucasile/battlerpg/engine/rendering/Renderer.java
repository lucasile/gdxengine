package com.lucasile.battlerpg.engine.rendering;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.lucasile.battlerpg.engine.ecs.component.components.RenderComponent;
import com.lucasile.battlerpg.engine.ecs.component.components.TransformComponent;
import com.lucasile.battlerpg.engine.settings.GameSettings;

import java.util.*;

public class Renderer {

    private static Renderer instance;

    private final int BATCH_RENDER_LIMIT;

    private Queue<RenderComponent> renderQueue;

    private SpriteBatch batch;

    public Renderer(SpriteBatch batch) {

        if (instance == null) {
            instance = this;
        } else {
            System.out.println("Cannot make more than one singleton instance (Renderer)");
        }

        BATCH_RENDER_LIMIT = GameSettings.BATCH_RENDER_LIMIT;
        renderQueue = new LinkedList<>();
        this.batch = batch;
    }

    public void render() {
        int queueSize = renderQueue.size();
        int limit = Math.min(queueSize, BATCH_RENDER_LIMIT);
        for (int i = 0; i < limit; i++) {
            RenderComponent renderComponent = renderQueue.remove();
            renderQueue.remove(renderComponent);
            TransformComponent transform = renderComponent.getTransform();
            Vector3 position = transform.getPosition();
            Vector2 dimensions = transform.getDimensions() ;
            batch.draw(renderComponent.getSprite(), position.x, position.y, dimensions.x, dimensions.y);
        }
        if (limit == BATCH_RENDER_LIMIT) {
            renderQueue.clear();
        }
    }

    public void addToQueue(RenderComponent renderComponent) {
        if (!renderQueue.offer(renderComponent)) {
            System.out.println("Render Queue Full");
        }
    }

    public static Renderer getInstance() {
        return instance;
    }
}
