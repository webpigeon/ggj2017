package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import javax.swing.plaf.metal.MetalIconFactory;

/**
 * Created by webpigeon on 21/01/17.
 */
public class PhysFactory {

    public static Ship buildWoodenBoat(World world) {

        BodyDef def = new BodyDef();
        def.position = new Vec2(10,10);
        def.bullet = true;
        def.angularDamping = 1f;

        Body body = world.createBody(def);
        body.setType(BodyType.DYNAMIC);

        FixtureDef fixDef = new FixtureDef();
        fixDef.friction = 0.8f;
        fixDef.restitution = 0.8f;
        fixDef.density = 1;
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(1f, 1f);
        fixDef.shape = ps;
        fixDef.restitution = 0; //boats are not made of rubber
        body.createFixture(fixDef);

        Ship ship = new Ship(body);
        body.setUserData(ship);

        return ship;
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

       body.setUserData(new Wall(body));

        return body;
    }

    public static Vortex buildVortex(World world, int x, int y, Vec2 force) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position = new Vec2(x,y);

        Body body = world.createBody(bodyDef);
        Vortex vortex = new Vortex(body, force);

        body.setUserData(vortex);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        CircleShape shape = new CircleShape();
        shape.m_radius = Vortex.RANGE;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

        return vortex;
    }

    public static Pickup buildPickup(World world, int x, int y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position = new Vec2(x, y);
        Body body = world.createBody(bodyDef);
        Pickup pickup = new Pickup(body, new Vec2(0.5f, 0.5f));

        body.setUserData(pickup);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        CircleShape shape = new CircleShape();
        shape.m_radius = 0.5f;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

        return pickup;
    }
}
