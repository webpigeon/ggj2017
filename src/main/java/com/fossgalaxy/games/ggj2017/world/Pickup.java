package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

import java.awt.*;

/**
 * Created by newowner on 22/01/2017.
 */
public class Pickup extends Entity {

    private final Vec2 dimensions;

    public Pickup(Body body, Vec2 dimensions) {
        super(body);
        this.dimensions = dimensions;
    }

    @Override
    public void draw(Graphics2D g2, GameWorld world) {
        Vec2 pos = world.translateWorldToScreen(body.getPosition());
        Vec2 screenDimensions = world.scaleWorldToScreen(dimensions);

        g2.setColor(Color.MAGENTA);
        g2.fillRect(
                (int) pos.x,
                (int) pos.y,
                (int) screenDimensions.x,
                (int) screenDimensions.y
        );
    }

    @Override
    public void onCollide(Entity entityB, Contact contact) {
        if(entityB instanceof Ship){
            System.out.println("Pickup located");
        }
    }
}

