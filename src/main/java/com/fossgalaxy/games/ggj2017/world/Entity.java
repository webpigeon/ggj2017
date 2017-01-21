package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * Created by webpigeon on 21/01/17.
 */
public class Entity {
    protected final Body body;

    public Entity(Body body) {
        this.body = body;
    }

    public void onCollide(Entity other, Manifold manifold) {

    }


    public Body getBody() {
        return body;
    }

    public void apply(World world) {
    }
}
