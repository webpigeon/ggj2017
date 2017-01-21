package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.ContactManager;
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
    private final CollisionManager manager;

    public GameWorld() {
        this.world = new World(new Vec2(0, 0));
        this.manager = new CollisionManager();
        world.setContactListener(manager);

        PhysFactory.buildBody(world);
        PhysFactory.buildVortex(world);
    }

    public void update() {
        Body body = world.getBodyList();
        while (body != null) {
            Entity entity = (Entity)body.getUserData();
            if (entity != null) {
                entity.apply(world);
            }
            body = body.getNext();
        }

        world.step(UPDATE_DELTA, VEL_ITER, POS_ITER);
    }

    public void debugRender(Graphics2D g2) {
        g2.setBackground(Color.BLACK);
        g2.fillRect(0, 0, 800, 600);

        g2.translate(10, 10);

        Body body = world.getBodyList();

        while(body != null) {

            System.out.println(body.getFixtureList().getAABB(0));

            Fixture fix = body.getFixtureList();
            while (fix != null) {
                AABB aabb = fix.getAABB(0);
                g2.setColor(Color.RED);
                g2.drawRect(
                        (int) aabb.lowerBound.x,
                        (int) aabb.lowerBound.y,
                        (int) aabb.upperBound.sub(aabb.lowerBound).x,
                        (int) aabb.upperBound.sub(aabb.lowerBound).y
                );
                fix = fix.getNext();
            }

            body = body.getNext();
        }
    }

}
