package com.fossgalaxy.games.ggj2017.controllers;

import com.fossgalaxy.games.ggj2017.world.GameWorld;
import com.fossgalaxy.games.ggj2017.world.Ship;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by webpigeon on 21/01/17.
 */
public class ShipController implements KeyListener {
    private GameWorld world;

    public ShipController(GameWorld world) {
        this.world = world;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Ship ship = world.getShip();

        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_Q: ship.setWindAngle(ship.getWindAngle() + 0.1f); break;
            case KeyEvent.VK_E: ship.setWindAngle(ship.getWindAngle() - 0.1f); break;
            case KeyEvent.VK_W: ship.forwards(); break;
            case KeyEvent.VK_A: ship.rotatePort(); break;
            case KeyEvent.VK_D: ship.rotateStarboard(); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
