package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

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

    public void onCollide(Entity other, Manifold manifold) {

    }


    public Body getBody() {
        return body;
    }

    public void apply(World world) {
        System.out.println(body.getLinearVelocity());
    }

    public void onCollideExit(Entity entityB, Manifold manifold) {

    }

    public void applyFriction(World world) {
        Vec2 vel = body.getLinearVelocity();
        Vec2 friction = vel.negate();
        friction.mul(WATER_FRICTION);

        body.applyForceToCenter(friction);
    }
}
