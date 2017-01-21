package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by webpigeon on 21/01/17.
 */
public class Island extends Entity {
    private float w;
    private float h;

    public Island(Body body, float w, float h) {
        super(body);
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics2D g2, GameWorld world) {

        AffineTransform at = g2.getTransform();

        Vec2 worldPos = world.translateWorldToScreen(body.getPosition());
        Vec2 worldSize = world.scaleWorldToScreen(new Vec2(w/2, h/2));

        g2.translate(worldPos.x, worldPos.y);
        g2.rotate(-body.getAngle());

        g2.setColor(Color.WHITE);
        g2.fillRect((int) (-worldSize.x), (int) (-worldSize.y), (int) worldSize.x * 2, (int) worldSize.y * 2);


        g2.setTransform(at);
    }
}
