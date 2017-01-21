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
    private static final Color BROWN = new Color(51, 0, 0);
    private float windDirection = (float)(Math.PI / 2 + Math.PI);
    private float windForce = 100;

    public Ship(Body body) {
        super(body);
    }

    public void draw(Graphics2D g2, GameWorld world) {

        AffineTransform at = g2.getTransform();

        Vec2 worldPos = world.translateWorldToScreen(body.getPosition());
        Vec2 worldSize = world.scaleWorldToScreen(new Vec2(1f, 1.5f));

        g2.translate(worldPos.x, worldPos.y);
        renderWindDirection(g2);

        g2.rotate(-body.getAngle());

        g2.setColor(BROWN);
        g2.fillRect((int) (-worldSize.x), (int) (-worldSize.y), (int) worldSize.x * 2, (int) worldSize.y * 2);

        /*g2.setColor(Color.WHITE);
        g2.drawLine(0, 0, 0, 100);*/

        g2.setTransform(at);

        renderHealth(g2, world);

    }

    public void setWindAngle(float wind) {
        this.windDirection = wind;
    }

    public Vec2 getWindVec() {
        return new Vec2(
                (float)(windForce * Math.cos(windDirection)),
                (float)(windForce * Math.sin(windDirection))
        );
    }

    protected void renderWindDirection(Graphics2D g) {

        int x = (int)(windForce * Math.cos(windDirection));
        int y = (int)(windForce * Math.sin(windDirection));

        g.setColor(Color.ORANGE);
        g.drawLine(0, 0, x, y);
    }

    protected void renderHealth(Graphics2D g2, GameWorld world) {
        Vec2 worldPos = world.translateWorldToScreen(body.getPosition());
        Vec2 worldSize = world.scaleWorldToScreen(new Vec2(1f, 1.5f));

        //health bar
        g2.setColor(Color.RED);
        g2.fillRect((int) worldPos.x - maxHealth / 2, (int) (worldPos.y + worldSize.y), maxHealth, 5);

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

    public boolean isAlive() {
        return health >= 0;
    }
}
