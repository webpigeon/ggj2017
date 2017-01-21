package com.fossgalaxy.games.ggj2017.world;

import org.jbox2d.common.Vec2;

/**
 * Created by newowner on 21/01/2017.
 */
public class VectorUtils {

    public static float distanceAloneLine(Vec2 p, Vec2 m, Vec2 s){
        Vec2 ps = s.sub(p);
        Vec2 pm = m.sub(p);
        double mps = Math.acos(Vec2.dot(ps, pm) / (ps.length() * pm.length()));
        if(mps == 0){
            return ps.length();
        }

        double a =  ((Math.sin(mps) * ps.length()) / Math.sin(Math.toRadians(90)));

        double pd = (float) Math.sqrt((ps.length() * ps.length()) - (a * a));

        return (float) pd;
    }

    /**
     *
     * @param p Position of vortex
     * @param m Position of max range in correct direction
     * @param s Position of the ship
     * @param theta The max allowed angle. Will allow anything +/- theta/2
     * @return
     */
    public static boolean thetaCheck(Vec2 p, Vec2 m, Vec2 s, double theta){
        Vec2 ps = s.sub(p);
        Vec2 pm = m.sub(p);
        double mps = Math.acos(Vec2.dot(ps, pm) / (ps.length() * pm.length()));

        return (theta / 2 > Math.abs(mps));
    }
}
