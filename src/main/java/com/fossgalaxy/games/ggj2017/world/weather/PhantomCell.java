package com.fossgalaxy.games.ggj2017.world.weather;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

/**
 * Created by newowner on 22/01/2017.
 */
public class PhantomCell {

    private Vec2 location;
    private final Vec2 dimensions;
    private final Vec2 forceToApply;
    private final World world;

    private static final Vec2 speed = new Vec2(1, 0);

    public PhantomCell(Vec2 location, Vec2 dimensions, Vec2 forceToApply, World world) {
        this.location = location;
        this.dimensions = dimensions;
        this.forceToApply = forceToApply;
        this.world = world;
    }

    public void update(){
        location = location.add(speed.mul(1/50));

        // Find vortex objects in range and alter them
        // See how the ship does it
    }
}
