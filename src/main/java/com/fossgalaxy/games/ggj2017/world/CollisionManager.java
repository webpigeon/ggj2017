package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Created by webpigeon on 21/01/17.
 */
public class CollisionManager implements ContactListener {
    public void beginContact(Contact contact) {

        //figure out who hit what
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        //exchange insurance details ;P
        Entity entityA = (Entity)bodyA.getUserData();
        Entity entityB = (Entity)bodyB.getUserData();

        if (entityA == null || entityB == null) {
            return;
        }

        entityA.onCollide(entityB, contact.getManifold());
        entityB.onCollide(entityA, contact.getManifold());
    }

    public void endContact(Contact contact) {
        //figure out who hit what
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        //exchange insurance details ;P
        Entity entityA = (Entity)bodyA.getUserData();
        Entity entityB = (Entity)bodyB.getUserData();

        if (entityA == null || entityB == null) {
            return;
        }

        entityA.onCollideExit(entityB, contact.getManifold());
        entityB.onCollideExit(entityA, contact.getManifold());
    }

    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
