package com.lucasile.battlerpg.engine.math;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class VectorUtils {

    public static Vector3 relativeVector(float x, float y, float z, Camera camera){
        return new Vector3(x + camera.position.x - camera.viewportWidth / 2,y + camera.position.y - camera.viewportHeight / 2, z);
    }

    public static Vector3 inverseRelativeVector(float x, float y, float z, Camera camera) {
        return new Vector3(x - camera.position.x + camera.viewportWidth / 2, y - camera.position.y + camera.viewportHeight / 2, z);
    }

}
