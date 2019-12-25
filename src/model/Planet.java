package model;

import math.Vector2;

public abstract class Planet implements Drawable {
    private double distanceA;
    private State state;

    public State getState() {
        return state;
    }

    public double getDistanceA() {
        return distanceA;
    }

    public void setDistanceA(double distanceA) {
        this.distanceA = distanceA;
    }

    public double getAngleA() {
        return angleA;
    }

    public void setAngleA(double angleA) {
        this.angleA = angleA;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    private double angleA;
    private Vector2 pos;
    public Vector2 getPos(){
        return pos;
    }
}
