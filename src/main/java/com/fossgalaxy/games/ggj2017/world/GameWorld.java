package com.fossgalaxy.games.ggj2017.world;

import com.fossgalaxy.games.ggj2017.mapGen.IslandMaker;
import com.fossgalaxy.games.ggj2017.mapGen.MapGenerator;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import java.awt.*;
import java.util.Random;

/**
 * Created by webpigeon on 21/01/17.
 */
public class GameWorld {
    private static final Vec2 REAL_ORIGIN = new Vec2();
    private static final float UPDATE_DELTA = 1/60f;
    private static final int VEL_ITER = 6;
    private static final int POS_ITER = 3;
    private static final float WIND_DELTA = 0.1f;

    private final World world;
    private final CollisionManager manager;

    private final Vec2 dimensions;
    private final Vec2 screenDimensions;

    private final Vortex[][] wind;
    private final Vec2 windDir;
    private final Random random;

    private Body player;

    public GameWorld() {
        this(new Vec2(25, 25), new Vec2(800, 800));
    }

    public GameWorld(Vec2 dimensions, Vec2 screenDimensions) {
        this.world = new World(new Vec2(0, 0));
        this.dimensions = dimensions;
        this.screenDimensions = screenDimensions;
        this.wind = new Vortex[150][150];
        this.windDir = new Vec2(0,0);
        this.random = new Random();

        //PhysFactory.buildBarrier(world, 0, 0, 150, 1);
        PhysFactory.buildBarrier(world, 151, -1, 1, 150);
        PhysFactory.buildBarrier(world, -1, -1, 1, 150);
        PhysFactory.buildBarrier(world, -1, -1, 150, 1);
        PhysFactory.buildBarrier(world, 151, -1, 150, 1);

        player = PhysFactory.buildWoodenBoat(world);

        buildEmptyField();

        this.manager = new CollisionManager();
        world.setContactListener(manager);

        addIslands();
    }

    private void buildRandomField() {
        Random random = new Random();

        for (int x=0; x<wind.length; x += 2) {
            for (int y=0; y<wind[x].length; y += 2) {
                PhysFactory.buildVortex(world, x, y, new Vec2((float) random.nextDouble() - 0.5f, (float) random.nextDouble() - 0.5f));
            }
        }
    }

    private void changeWind(float windX, float windY) {
        for (int x=0; x<wind.length; x += 2) {
            for (int y=0; y<wind[x].length; y += 2) {
                wind[x][y].setForce(windX, windY);
            }
        }
        windDir.set(windX, windY);
    }

    private void buildEmptyField() {
        for (int x=0; x<wind.length; x += 2) {
            for (int y=0; y<wind[x].length; y += 2) {
                wind[x][y] = PhysFactory.buildVortex(world, x, y, new Vec2(windDir));
            }
        }
    }

    private void buildStaticField() {
        Vec2 windDirection = new Vec2(0.5f, 0.5f);

        for (int x=0; x<wind.length; x += 2) {
            for (int y=0; y<wind[x].length; y += 2) {
                wind[x][y] = PhysFactory.buildVortex(world, x, y, windDirection);
            }
        }
    }

    private void addIslands() {
        boolean[][] map = MapGenerator.generate((int) dimensions.x, (int) dimensions.y, 1);
        IslandMaker.makeIslandsRunLength(map, 0.25f, world);
    }

    public void setPlayer(Body player) {
        this.player = player;
    }

    public void update() {
        world.clearForces();

        windDir.x += WIND_DELTA * random.nextGaussian();
        windDir.y += WIND_DELTA * random.nextGaussian();
        windDir.normalize();

        changeWind(windDir.x, windDir.y);

        Body body = world.getBodyList();
        while (body != null) {
            Entity entity = (Entity) body.getUserData();
            if (entity != null) {
                entity.apply(world);
            }
            body = body.getNext();
        }

        //apply friction
        body = world.getBodyList();
        while (body != null) {
            Entity entity = (Entity) body.getUserData();
            if (entity != null) {
                entity.applyFriction(world);
            }
            body = body.getNext();
        }

        world.step(UPDATE_DELTA, VEL_ITER, POS_ITER);
    }

    public void debugRender(Graphics2D g2) {
        g2.setBackground(Color.BLACK);
        g2.fillRect(0, 0, (int) screenDimensions.x, (int) screenDimensions.y);

        //g2.translate(10, 10);

        Body body = world.getBodyList();

        while (body != null) {
            Fixture fix = body.getFixtureList();
            while (fix != null) {
                AABB aabb = fix.getAABB(0);
                Vec2 aabbLower = translateWorldToScreen(aabb.lowerBound);
                Vec2 aabbDimensions = scaleWorldToScreen(aabb.upperBound.sub(aabb.lowerBound));

                g2.setColor(Color.RED);
                g2.drawRect(
                        (int) aabbLower.x,
                        (int) aabbLower.y,
                        (int) aabbDimensions.x,
                        (int) aabbDimensions.y
                );
                fix = fix.getNext();
            }

            body = body.getNext();
        }
    }

    public Vec2 translateWorldToScreen(Vec2 input) {
        float ratioX = screenDimensions.x / dimensions.x;
        float ratioY = screenDimensions.y / dimensions.y;

        Vec2 origin = REAL_ORIGIN;
        if (player != null) {
            origin = new Vec2(player.getPosition());
        }

        return new Vec2(
                (input.x - origin.x) * ratioX + (screenDimensions.x / 2),
                (input.y - origin.y) * ratioY + (screenDimensions.y / 2)
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

    public void render(Graphics2D g2) {
        g2.setBackground(Color.BLACK);
        g2.fillRect(0, 0, (int) screenDimensions.x, (int) screenDimensions.y);

        //g2.translate(10, 10);

        Body body = world.getBodyList();

        while(body != null) {
            Entity entity = (Entity)body.getUserData();
            if (entity != null) {
                entity.draw(g2, this);
            }

            body = body.getNext();
        }
    }
}
