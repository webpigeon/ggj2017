package com.fossgalaxy.games.ggj2017.mapGen;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.util.ArrayList;

import com.fossgalaxy.games.ggj2017.world.Entity;
import com.fossgalaxy.games.ggj2017.world.Island;

/**
 * Created by newowner on 21/01/2017.
 */
public class IslandMaker {

    public static ArrayList<Body> makeIslands(boolean[][] map, float numPerMeter, World world) {
        ArrayList<Body> bodies = new ArrayList<Body>();

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y]) {
                    bodies.add(createBody(
                            x / numPerMeter,
                            y / numPerMeter,
                            1 / numPerMeter,
                            1 / numPerMeter,
                            world
                    ));
                }
            }
        }

        return bodies;
    }

    private static Body createBody(float x, float y, float w, float h, World world) {
        BodyDef def = new BodyDef();
        def.position = new Vec2(x, y);
        def.type = BodyType.STATIC;


        Body body = world.createBody(def);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(h / 2f, w / 2f);
        fixtureDef.shape = ps;
        fixtureDef.restitution = 0;

        body.createFixture(fixtureDef);

        body.setUserData(new Island(body, w, h));
        return body;
    }
}
