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
    private final CollisionManager manager;

    private final Vec2 dimensions;
    private final Vec2 screenDimensions;

    public GameWorld() {
        this(new Vec2(100, 100), new Vec2(800, 600));
    }

    public GameWorld(Vec2 dimensions, Vec2 screenDimensions){
        this.world = new World(new Vec2(0, 0));
        this.dimensions = dimensions;
        this.screenDimensions = screenDimensions;
        PhysFactory.buildBody(world);
        PhysFactory.buildVortex(world);

        this.manager = new CollisionManager();
        world.setContactListener(manager);
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
        g2.fillRect(0, 0, (int)screenDimensions.x, (int) screenDimensions.y);

        g2.translate(10, 10);

        Body body = world.getBodyList();

        while(body != null) {
            Fixture fix = body.getFixtureList();
            while (fix != null) {
                AABB aabb = fix.getAABB(0);
                Vec2 aabbLower = translateWorldToScreen(aabb.lowerBound);
                Vec2 aabbDimensions = scaleWorldToScreen(aabb.upperBound.sub(aabb.lowerBound));

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

    public Vec2 translateWorldToScreen(Vec2 input) {
        return new Vec2(
                ((input.x * screenDimensions.x) / dimensions.x),
                (screenDimensions.y - ((input.y * screenDimensions.y) / dimensions.y))
        );
    }

    public Vec2 translateScreenToWorld(Vec2 input) {
        return new Vec2(
                ((input.x * dimensions.x) / screenDimensions.x),
                (((screenDimensions.y - input.y) * dimensions.y) / screenDimensions.y)
        );
    }

    public Vec2 scaleScreenToWorld(Vec2 input) {
        return new Vec2(
                ((input.x * dimensions.x) / screenDimensions.x),
                ((input.y * dimensions.y) / screenDimensions.y)
        );
    }

    public Vec2 scaleWorldToScreen(Vec2 input) {
        return new Vec2(
                ((input.x * screenDimensions.x) / dimensions.x),
                ((input.y * screenDimensions.y) / dimensions.y)
        );
    }

}
