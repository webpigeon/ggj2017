package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by webpigeon on 21/01/17.
 */
public class Vortex extends Entity {
    private Vec2 force;
    private List<Body> inContact;

    public Vortex(Body body, Vec2 force) {
        super(body);
        this.inContact = new ArrayList<>();
        this.force = force;
    }

    @Override
    public void draw(Graphics2D g2, GameWorld world) {

        Vec2 pos = world.translateWorldToScreen(body.getPosition());
        Vec2 endPos = world.translateWorldToScreen(new Vec2(force).add(body.getPosition()));

        g2.setColor(Color.RED);
        g2.drawLine((int)pos.x, (int)pos.y, (int)endPos.x, (int)endPos.y);
    }

    public void apply(World world) {

        float angle = (float)Math.atan2(force.y, force.x);

        for (Body body : inContact) {
            Vec2 normMag = new Vec2(force);
            normMag.normalize();

            float forceMag = body.getMass();

            Vec2 bodyPos = new Vec2(body.getPosition());
            Vec2 deltaPos = bodyPos.sub(getBody().getPosition());

            float deltaMag = deltaPos.length();

            float mag = forceMag / deltaMag;
            normMag.mul(mag);

            body.applyForce(normMag, body.getWorldCenter());

            //try to fake the next angle
            float nextAngle = body.getAngle() + body.getAngularVelocity() / 3f;
            float totalRotation = angle - nextAngle;
            while (totalRotation < Math.toRadians(-180)) totalRotation += Math.toRadians(360);
            while (totalRotation > Math.toRadians(180)) totalRotation -= Math.toRadians(360);
            body.applyTorque(totalRotation < 0 ? -100 : 100);
        }
    }

    @Override
    public void onCollide(Entity other, Contact contact) {
        super.onCollide(other, contact);
        inContact.add(other.getBody());
    }

    @Override
    public void onCollideExit(Entity other, Contact contact) {
        super.onCollide(other, contact);
        inContact.remove(other.getBody());
    }
}
