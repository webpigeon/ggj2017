package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import java.util.List;

/**
 * Created by webpigeon on 21/01/17.
 */
public class Vortex extends Entity {
    private Vec2 force;
    private List<Body> inContact;

    public Vortex(Body body) {
        super(body);
    }

    public void apply(World world) {


        for (Body body : inContact) {
            Vec2 normMag = new Vec2(force);
            normMag.normalize();

            float forceMag = force.length();

            Vec2 bodyPos = new Vec2(body.getPosition());
            Vec2 deltaPos = bodyPos.sub(getBody().getPosition());

            float deltaMag = deltaPos.length();

            float mag = forceMag / deltaMag;
            normMag.mul(mag);

            body.applyForceToCenter(normMag);
        }
    }

    @Override
    public void onCollide(Entity other, Manifold manifold) {
        super.onCollide(other, manifold);

        inContact.add(other.getBody());
    }
}
