package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import java.awt.*;

/**
 * Created by webpigeon on 21/01/17.
 */
public class GameWorld {
    private static final float UPDATE_DELTA = 1/50f;
    private static final int VEL_ITER = 6;
    private static final int POS_ITER = 3;
    private final World world;

    public GameWorld() {
        this.world = new World(new Vec2(0, 0));

        PhysFactory.buildBody(world);
    }

    public void update() {
        world.step(UPDATE_DELTA, VEL_ITER, POS_ITER);
    }

    public void debugRender(Graphics2D g2) {
        g2.setBackground(Color.BLACK);
        g2.fillRect(0, 0, 800, 600);

        Body body = world.getBodyList();

        while(body != null) {

            System.out.println(body.getFixtureList().getAABB(0));

            Fixture fix = body.getFixtureList();
            while (true) {
                AABB aabb = fix.getAABB(0);
                g2.setColor(Color.RED);
                g2.fillRect(
                        (int) aabb.lowerBound.x,
                        (int) aabb.lowerBound.y,
                        (int) aabb.upperBound.sub(aabb.lowerBound).x,
                        (int) aabb.upperBound.sub(aabb.lowerBound).y
                );
                fix = fix.getNext();
                if (fix == null) break;
            }

            body = body.getNext();
        }
    }

}
