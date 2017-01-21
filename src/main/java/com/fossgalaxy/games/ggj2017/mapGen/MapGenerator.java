package com.fossgalaxy.games.ggj2017.mapGen;

import com.fossgalaxy.games.ggj2017.mapGen.noise.SimplexNoise;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by newowner on 21/01/2017.
 */
public class MapGenerator {

    /**
     * generate the map
     *
     * @param width       in meters of the world
     * @param height      in meters of the world
     * @param numPerMeter number of boxes in each meter of world
     */
    public static boolean[][] generate(int width, int height, int numPerMeter) {

        float[][] noise = SimplexNoise.generateNoise(width * numPerMeter, height * numPerMeter);
        float seaLevel = 0.72f;

        boolean[][] map = new boolean[width * numPerMeter][height * numPerMeter];
        for(int i = 0; i < noise.length; i++){
            for(int j = 0; j < noise[i].length; j++){
                map[i][j] = noise[i][j] > seaLevel;
            }
        }

        return map;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int width = 800, height = 800;

        frame.setPreferredSize(new Dimension(width, height));

        final int scale = 25;
        final boolean[][] map = generate(width / scale, height / scale, 1);

        ArrayList<Body> bodies = IslandMaker.makeIslands(map, 1, new World(new Vec2(0, 0)));
        System.out.println(bodies.size() + ":" + getMass(bodies));
        bodies = IslandMaker.makeIslandsRunLength(map, 1, new World(new Vec2(0, 0)));
        System.out.println(bodies.size() + ":" + getMass(bodies));
        frame.add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(int x = 0; x < map.length; x++){
                    for(int y = 0; y < map[x].length; y++){
                        g.setColor(map[x][y] ? Color.GREEN : Color.CYAN);
                        g.fillRect(x * scale, y * scale, scale, scale);
                    }
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

//    private static double getMass(ArrayList<Body> bodies){
//        return bodies.stream().map(Body::getMass).mapToDouble(Float::doubleValue).sum();
//    }

    private static float getMass(ArrayList<Body> bodies){
        float sum = 0;
        for(Body body : bodies) sum += body.getMass();
        return sum;
    }
}
