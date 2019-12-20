package model;

import math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovableCircle extends Movable {
    private BufferedImage bi;

    public MovableCircle(double m, double r, Vector2 position) {
        super(m, r, position);
    }

    public MovableCircle(double m, double r, Vector2 position,BufferedImage bi){
        super(m,r,position);
        this.bi = bi;
    }

    public void draw(Graphics2D g,int x,int y){
        g.drawImage(bi,x,y,null);
    }
}
