package com.fossgalaxy.games.ggj2017.world;

import com.fossgalaxy.games.ggj2017.sprites.SpriteManager;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by newowner on 22/01/2017.
 */
public class Pickup extends Entity {

    private final Vec2 dimensions;
    private static BufferedImage sprite;

    static {
        try {
            BufferedImage image = ImageIO.read(Pickup.class.getClassLoader().getResourceAsStream("spritesheet.png"));
            sprite = image.getSubimage(733, 188, 16, 16);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Pickup(Body body, Vec2 dimensions) {
        super(body);
        this.dimensions = dimensions;


    }

    @Override
    public void apply(World world) {

        //if we have no health, reap
        if (health == 0) {
            world.destroyBody(body);
        }
    }

    @Override
    public void draw(Graphics2D g2, GameWorld world) { // 733, 188, 16, 16
        Vec2 pos = world.translateWorldToScreen(body.getPosition());
        Vec2 screenDimensions = world.scaleWorldToScreen(dimensions);

        if (sprite !=null){
            g2.drawImage(sprite,
                    (int) pos.x,
                    (int) pos.y,
                    (int) screenDimensions.x,
                    (int) screenDimensions.y,
                    null);
        }else{
            g2.setColor(Color.MAGENTA);
            g2.fillRect(
                    (int) pos.x,
                    (int) pos.y,
                    (int) screenDimensions.x,
                    (int) screenDimensions.y
            );
        }
    }

    @Override
    public void onCollide(Entity entityB, Contact contact) {
        if (entityB instanceof Ship) {
            health = 0;
            Ship ship = (Ship) entityB;
            ship.addScore(+1);
        }
    }
}

