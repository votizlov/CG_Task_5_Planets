/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import math.Rectangle;
import math.Vector2;
import model.*;
import timers.AbstractWorldTimer;
import timers.UpdateWorldTimer;
import utils2d.ScreenConverter;
import utils2d.ScreenPoint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements ActionListener,
        MouseListener, MouseMotionListener, MouseWheelListener,KeyListener {
    private ScreenConverter sc;
    private World w;
    private AbstractWorldTimer uwt;
    private Timer drawTimer;

    public DrawPanel() {
        super();
        Field f = new Field(
                new Rectangle(0, 10, 10, 10),
                0.1, 9.8);
        BufferedImage img = new BufferedImage(5,5,BufferedImage.TYPE_INT_RGB);
        w = new World(new MovableCircle(1, 0.3, f.getRectangle().getCenter(),img), f);
        sc = new ScreenConverter(f.getRectangle(), 450, 450);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addKeyListener(this);

        (uwt = new UpdateWorldTimer(w, 10)).start();
        drawTimer = new Timer(40, this);
        drawTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        sc.setHs(getHeight());
        sc.setWs(getWidth());
        w.draw((Graphics2D) bi.getGraphics(), sc);
        g.drawImage(bi, 0, 0, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int direction = 0;
        if (e.getButton() == MouseEvent.BUTTON1)
            direction = 1;
        else if (e.getButton() == MouseEvent.BUTTON3)
            direction = -1;
        w.getExternalForce().setValue(10 * direction);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        w.getExternalForce().setValue(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double oldMu = w.getF().getMu();
        oldMu = Math.round(oldMu * 100 + e.getWheelRotation()) * 0.01;

        if (oldMu < -1)
            oldMu = -1;
        else if (oldMu > 1)
            oldMu = 1;
        else if (Math.abs(oldMu) < 0.005)
            oldMu = 0;
        w.getF().setMu(oldMu);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Vector2 dir = new Vector2(0,0);
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                dir = w.getM().getPosition().add(new Vector2(0,5));
                break;
            case KeyEvent.VK_DOWN:
                dir = w.getM().getPosition().add(new Vector2(0,-5));
                break;
            case KeyEvent.VK_LEFT:
                dir = w.getM().getPosition().add(new Vector2(-5,0));
                break;
            case KeyEvent.VK_RIGHT:
                dir = w.getM().getPosition().add(new Vector2(5,0));
                break;
        }
        w.getExternalForce().setLocation(dir);
        w.getExternalForce().setValue(10);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        w.getExternalForce().setValue(0);
    }
}
