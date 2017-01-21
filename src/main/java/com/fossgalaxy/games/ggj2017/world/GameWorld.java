package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

/**
 * Created by webpigeon on 21/01/17.
 */
public class GameWorld {
    private final World world;

    public GameWorld() {
        this.world = new World(new Vec2(0, 0));
    }

}
