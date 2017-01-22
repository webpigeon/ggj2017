package com.fossgalaxy.games.ggj2017.world.weather;

import com.fossgalaxy.games.ggj2017.world.GameWorld;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

/**
 * Created by newowner on 22/01/2017.
 */
public class PhantomCell {

    private Vec2 location;
    private final int size;
    private final Vec2 forceToApply;
    private final GameWorld world;

    private static final Vec2 speed = new Vec2(1, 0);

    public PhantomCell(Vec2 location, int size, Vec2 forceToApply, GameWorld world) {
        this.location = location;
        this.size = size;
        this.forceToApply = forceToApply;
        this.world = world;
    }

    public void update(){
        location = location.add(speed.mul(1/50));
        world.changeWind(forceToApply.x, forceToApply.y, (int)location.x, (int)location.y, size);
    }
}
