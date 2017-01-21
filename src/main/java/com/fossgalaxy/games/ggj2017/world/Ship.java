package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by webpigeon on 21/01/17.
 */
public class Ship extends Entity {
    public Ship(Body body) {
        super(body);
    }

    public void draw(Graphics2D g2, GameWorld world) {

        AffineTransform at = g2.getTransform();

        Vec2 worldPos = world.translateWorldToScreen(body.getPosition());
        Vec2 worldSize = world.scaleWorldToScreen(new Vec2(1f, 1.5f));

        g2.translate(worldPos.x, worldPos.y);
        g2.rotate(-body.getAngle());

        g2.setColor(Color.BLACK);
        g2.fillRect((int) (-worldSize.x), (int) (-worldSize.y), (int) worldSize.x * 2, (int) worldSize.y * 2);

        g2.setColor(Color.WHITE);
        g2.drawLine(0, 0, 0, -100);

        g2.setTransform(at);

        renderHealth(g2, world);
    }

    protected void renderHealth(Graphics2D g2, GameWorld world) {
        Vec2 worldPos = world.translateWorldToScreen(body.getPosition());
        Vec2 worldSize = world.scaleWorldToScreen(new Vec2(1f, 1.5f));

        //health bar
        g2.setColor(Color.RED);
        g2.fillRect((int) worldPos.x - maxHealth/2, (int) (worldPos.y + worldSize.y), maxHealth, 5);

        g2.setColor(Color.GREEN);
        g2.fillRect((int) worldPos.x - maxHealth / 2, (int) (worldPos.y + worldSize.y), health, 5);
    }

    @Override
    public void onCollide(Entity other, Contact contact) {
        if (health == 0) {
            return;
        }

        if (other instanceof Island || other instanceof Wall) {
            health--;
            System.out.println("hit: "+other);
            if (health == 0) {
                System.out.println("player is dead");
            }
        }
    }
}
