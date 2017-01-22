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
    public static float thetaCheck(Vec2 p, Vec2 m, Vec2 s){
        Vec2 ps = s.sub(p);
        Vec2 pm = m.sub(p);
        double mps = Math.acos(Vec2.dot(ps, pm) / (ps.length() * pm.length()));

        return (float)Math.abs(mps);
    }

    private static final float RIGHT = 0;
    private static final float DOWN = (float) (Math.PI / 2);
    private static final float Left = (float) (Math.PI );
    private static final float Up = (float) (3 * Math.PI / 2 );



    public static float windCalculation(float windAngle, float shipAngle){

        float difference = windAngle - shipAngle;
        float fraction = (float)(difference / Math.PI);
//        fraction = 1 - fraction;
        float quarter = 0.5f;
        System.out.println("fraction: " + fraction);

        if(Math.abs(fraction) == 0) return 1;
        if(Math.abs(fraction) == 1) return 0.75f;
        if(Math.abs(fraction) == 0.5f) return 0.25f;
        if(Math.abs(fraction) == 1.5f) return 0.25f;

        if(fraction < 0){
            System.out.println("Top Quadrant");
            if(fraction <  -quarter){
                System.out.println("TL");
                return (((fraction + quarter) * 0.5f) +0.25f);
            }else{
                System.out.println("TR");
                return (fraction * 0.75f) + 0.25f;
            }
        }else {
            System.out.println("Bottom Quadrant");
            if (fraction > quarter) {
                System.out.println("BL");
                return ((fraction - quarter) * 0.5f) + 0.25f;
            } else {
                System.out.println("BR");
                System.out.println(fraction);
                return ((fraction * 2) * 0.75f) + 0.25f;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(windCalculation(0, 0)); // 1
        System.out.println(windCalculation(0, (float)Math.PI / 4)); //0.625
        System.out.println(windCalculation(0, (float)-Math.PI / 4)); //0.625
        System.out.println(windCalculation(0, (float)(3 * Math.PI / 2))); //0.25
        System.out.println(windCalculation(0, (float)(-3 * Math.PI / 2))); // 0.25
        System.out.println(windCalculation((float)Math.PI, 0)); // 0.75
        System.out.println(windCalculation((float)-Math.PI, 0)); // 0.75
        System.out.println(windCalculation(0, (float)Math.PI / 2)); // 0.25
        System.out.println(windCalculation(0, (float)-Math.PI / 2)); // 0.25
    }
}
