package com.fossgalaxy.games.ggj2017;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println("Hello World!");


        Frame frame = new Frame();
        frame.setPreferredSize(new Dimension(800, 600));
        Canvas canvas = new Canvas();

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        BufferStrategy bs = canvas.getBufferStrategy();

        boolean running = true;

        while (running) {
            Graphics g = bs.getDrawGraphics();

            g.setColor(Color.BLACK);
            g.drawRect(0, 0, 10, 10);

            bs.show();

            Thread.sleep(1000/50);
        }
    }
}
