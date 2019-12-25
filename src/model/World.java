/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import math.Vector2;
import utils2d.ScreenConverter;
import utils2d.ScreenPoint;

import java.awt.*;
import java.util.LinkedList;

import static java.lang.Math.pow;

public class World implements IWorld {

    private Field f;
    private ForceSource externalForce;
    private LinkedList<Planet> planets;
    private LinkedList<Satellite> satellites;
    private Vector2 center;
    private double massOfTheSunKg = 100000;
    private double gravitationalConstant = 6.674*pow(10,-11);

    public World(Field f) {
        this.f = f;
        this.externalForce = new ForceSource(f.getRectangle().getCenter());
        this.planets = new LinkedList<>();
        this.satellites = new LinkedList<>();
        for (Planet p: planets
        ) {
            function calculateDistanceAcceleration(state) {
            return state.distance.value * pow(state.angle.speed, 2) -
                    (constants.gravitationalConstant * state.massOfTheSunKg) / pow(state.distance.value, 2);
}
            function calculateAngleAcceleration(state) {
            return -2.0 * state.distance.speed * state.angle.speed / state.distance.value;
}
        }

        //satellite calc
        for (Satellite s:satellites
        ) {
            function calculateDistanceAcceleration(state) {
            return state.distance.value * pow(state.angle.speed, 2) -
                    (constants.gravitationalConstant * state.massOfTheSunKg) / pow(state.distance.value, 2);
}
            function calculateAngleAcceleration(state) {
            return -2.0 * state.distance.speed * state.angle.speed / state.distance.value;
}
        }
    }
    function newValue(currentValue, deltaT, derivative) {
        return currentValue + deltaT * derivative;
    }
    
    /**
     * Метод обновления состояния мира за указанное время
     * @param dt Промежуток времени, за который требуется обновить мир.
     */
    public void update(double dt) {
//https://evgenii.com/blog/earth-orbit-simulation/
        //planets calc
        for (Planet p: planets
             ) {
            var distanceAcceleration = calculateDistanceAcceleration(state);

            state.distance.speed = newValue(state.distance.speed,
                    deltaT, distanceAcceleration);

            state.distance.value = newValue(state.distance.value,
                    deltaT, state.distance.speed);

            var angleAcceleration = calculateAngleAcceleration(state);

            state.angle.speed = newValue(state.angle.speed,
                    deltaT, angleAcceleration);

            state.angle.value = newValue(state.angle.value,
                    deltaT, state.angle.speed);
        }

        //satellite calc
        for (Satellite s:satellites
             ) {
            var distanceAcceleration = calculateDistanceAcceleration(state);

            state.distance.speed = newValue(state.distance.speed,
                    deltaT, distanceAcceleration);

            state.distance.value = newValue(state.distance.value,
                    deltaT, state.distance.speed);

            var angleAcceleration = calculateAngleAcceleration(state);

            state.angle.speed = newValue(state.angle.speed,
                    deltaT, angleAcceleration);

            state.angle.value = newValue(state.angle.value,
                    deltaT, state.angle.speed);
        }
    }
    
    /**
     * Метод рисует ткущее состояние мира.
     * На самом деле всю логику рисования стоит вынести из этого класса
     * куда-нибудь в WroldDrawer, унаследованный от IDrawer
     * @param g Графикс, на котором надо нарисовать текущее состояние.
     * @param sc Актуальный конвертер координат.
     */
    public void draw(Graphics2D g, ScreenConverter sc) {
        ScreenPoint tl = sc.r2s(f.getRectangle().getTopLeft());
        int w = sc.r2sDistanceH(f.getRectangle().getWidth());
        int h = sc.r2sDistanceV(f.getRectangle().getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(tl.getI(), tl.getJ(), w, h);
        g.setColor(Color.RED);
        g.drawRect(tl.getI(), tl.getJ(), w, h);

        for (Planet p:planets
             ) {
            p.draw(g,sc.r2s(p.getPos()).getI(),sc.r2s(p.getPos()).getJ());
        }

        for (Satellite s:satellites
             ) {
            s.draw(g,sc.r2s(s.getPos()).getI(),sc.r2s(s.getPos()).getJ());
        }

        g.drawString(String.format("Mu=%.2f", f.getMu()), 10, 30);
        g.drawString(String.format("F=%.0f", externalForce.getValue()), 10, 50);
    }

    public Field getF() {
        return f;
    }

    public void setF(Field f) {
        this.f = f;
    }
    
    public ForceSource getExternalForce() {
        return externalForce;
    }

    public void addPlanet(Planet p){
        planets.add((p));
    }

    public void addSatellite(Satellite s){
        satellites.add(s);
    }
}
