package com.nyvraxx.spacebuilder;

import com.badlogic.gdx.math.Vector2;

public class Random {
    private static final java.util.Random RANDOM = new java.util.Random();

    public static float nextFloat(float bound){
        return RANDOM.nextFloat(bound);
    }
}
