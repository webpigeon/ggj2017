package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by webpigeon on 21/01/17.
 */
public class Entity {
    private static final float WATER_FRICTION = 0.1f;
    protected final Body body;
    protected final List<Component> componentList;

    public Entity(Body body) {
        this.body = body;
        this.componentList = new ArrayList<>();
    }

    public void onCollide(Entity other, Contact contact) {

    }


    public Body getBody() {
        return body;
    }

    public void apply(World world) {
        //System.out.println(body.getLinearVelocity());
    }

    public void onCollideExit(Entity entityB, Contact contact) {

    }

    public void applyFriction(World world) {
        Vec2 vel = body.getLinearVelocity();
        Vec2 friction = vel.negate();
        friction.mul(WATER_FRICTION);

        body.applyForceToCenter(friction);
    }

    public void draw(Graphics2D g2, GameWorld world) {

        AffineTransform at = g2.getTransform();

        Vec2 worldPos = world.translateWorldToScreen(body.getPosition());
        Vec2 worldSize = world.scaleWorldToScreen(new Vec2(1f, 1.5f));

        g2.translate(worldPos.x, worldPos.y);
        g2.rotate(-body.getAngle());

        g2.setColor(Color.BLACK);
        g2.fillRect((int) (-worldSize.x), (int) (-worldSize.y), (int) worldSize.x * 2, (int) worldSize.y * 2);


        g2.setTransform(at);
    }
}
