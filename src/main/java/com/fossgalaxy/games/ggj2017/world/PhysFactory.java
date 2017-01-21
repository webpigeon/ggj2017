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
        fixDef.friction = 0.99f;
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(10f, 10f);
        fixDef.shape = ps;
        body.createFixture(fixDef);

        body.setUserData(new Entity(body));

        return body;
    }

    public static void buildVortex(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position = new Vec2(0,0);

        Body body = world.createBody(bodyDef);
        body.setUserData(new Vortex(body));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        CircleShape shape = new CircleShape();
        shape.m_radius = 5;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
    }
}
