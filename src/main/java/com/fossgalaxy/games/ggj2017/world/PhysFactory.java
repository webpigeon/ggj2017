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
        def.position = new Vec2(0,0);

        Body body = world.createBody(def);
        body.setType(BodyType.DYNAMIC);

        FixtureDef fixDef = new FixtureDef();
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(5f, 2f);
        fixDef.shape = ps;
        body.createFixture(fixDef);

        return body;
    }

    public static void buildVortex(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position = new Vec2(0,0);

        Body body = world.createBody(bodyDef);
        body.setType(BodyType.STATIC);
        body.setUserData(new Vortex(body));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        CircleShape shape = new CircleShape();
        shape.m_radius = 5;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

    }
}
