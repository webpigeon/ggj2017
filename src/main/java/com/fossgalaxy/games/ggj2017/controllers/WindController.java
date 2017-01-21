package com.fossgalaxy.games.ggj2017.controllers;

import com.fossgalaxy.games.ggj2017.world.GameWorld;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by newowner on 21/01/2017.
 */
public class WindController implements MouseListener {

    private int clickedX;
    private int clickedY;
    private final GameWorld world;

    public WindController(GameWorld world) {
        this.world = world;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickedX = e.getX();
        clickedY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int releasedX = e.getX();
        int releasedY = e.getY();
        Vec2 force = new Vec2(releasedX - clickedX, releasedY - clickedY);
        force = force.mul(0.01f);
        world.changeWind(force.x, force.y);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
