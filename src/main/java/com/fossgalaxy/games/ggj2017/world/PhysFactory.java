package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**
 * Created by webpigeon on 21/01/17.
 */
public class PhysFactory {

    public static Body buildWoodenBoat(World world) {

        BodyDef def = new BodyDef();
        def.position = new Vec2(10,10);
        def.bullet = true;

        Body body = world.createBody(def);
        body.setType(BodyType.DYNAMIC);

        FixtureDef fixDef = new FixtureDef();
        fixDef.friction = 0.8f;
        fixDef.restitution = 0.8f;
        fixDef.density = 1;
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(1f, 1.5f);
        fixDef.shape = ps;
        fixDef.restitution = 0; //boats are not made of rubber
        body.createFixture(fixDef);

        body.setUserData(new Entity(body));

        return body;
    }

    public static Body buildBarrier(World world, int x, int y, int width, int height) {
        BodyDef def = new BodyDef();
        def.position = new Vec2(x+width/2,y+height/2);

        Body body = world.createBody(def);
        body.setType(BodyType.STATIC);

        FixtureDef fixDef = new FixtureDef();
        fixDef.friction = 0.8f;
        fixDef.restitution = 0.8f;
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width/2f, height/2f);
        fixDef.shape = ps;
        body.createFixture(fixDef);

       // body.setUserData(new Entity(body));

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
