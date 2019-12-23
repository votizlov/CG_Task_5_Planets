package model;

import math.Vector2;

public abstract class Planet implements Drawable {
    private Vector2 pos;
    public Vector2 getPos(){
        return pos;
    }
}
