package com.fossgalaxy.games.ggj2017.world;

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
        ps.setAsBox(100f, 100f);
        fixDef.shape = ps;
        body.createFixture(fixDef);

        return body;
    }
}
