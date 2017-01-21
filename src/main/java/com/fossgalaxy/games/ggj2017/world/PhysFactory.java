package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**
 * Created by webpigeon on 21/01/17.
 */
public class PhysFactory {

    public static Body buildBody(World world) {

        BodyDef def = new BodyDef();
        def.position = new Vec2(10,10);

        Body body = world.createBody(def);
        body.setType(BodyType.DYNAMIC);

        FixtureDef fixDef = new FixtureDef();
        fixDef.friction = 0.8f;
        fixDef.restitution = 0.8f;
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(2f, 3f);
        fixDef.shape = ps;
        body.createFixture(fixDef);

        body.setUserData(new Entity(body));

        return body;
    }

    public static void buildVortex(World world, int x, int y, Vec2 force) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position = new Vec2(x,y);

        Body body = world.createBody(bodyDef);
        body.setUserData(new Vortex(body, force));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        CircleShape shape = new CircleShape();
        shape.m_radius = 0.5f;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
    }
}
