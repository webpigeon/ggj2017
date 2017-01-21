package com.fossgalaxy.games.ggj2017;

import com.fossgalaxy.games.ggj2017.world.GameWorld;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

/**
 * Hello world!
 *
 */
public class App implements Runnable, WindowListener {
    private final GameWorld world;
    private final Frame frame;
    private final Canvas canvas;
    private boolean running = true;

    public App() {
        this.frame = new Frame();
        frame.addWindowListener(this);
        this.canvas = buildView(frame);
        this.world = new GameWorld();
    }

    public static void main( String[] args ) throws InterruptedException {
        System.out.println("Hello World!");

        App app = new App();
        app.run();

    }

    public static Canvas buildView(Frame frame) {
        frame.setPreferredSize(new Dimension(800, 800));
        Canvas canvas = new Canvas();

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        return canvas;
    }


    public void run() {
        long lastFPS = System.currentTimeMillis();
        int frames = 0;


        try {
            while (running) {

                world.update();

                BufferStrategy bs = canvas.getBufferStrategy();
                Graphics2D g = (Graphics2D)bs.getDrawGraphics();

                g.setColor(new Color(99, 197, 207));
                //g.drawRect(0, 0, 10, 10);
                //g.scale(2, 2);

                world.debugRender(g);

                g.dispose();

                bs.show();
                frames++;

                long currentTime = System.currentTimeMillis();
                if (currentTime - lastFPS > 1000) {
                    System.out.println(frames);
                    lastFPS = currentTime;
                    frames = 0;
                }

                Thread.sleep(1000 / 60);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        frame.dispose();
    }

    public void windowOpened(WindowEvent windowEvent) {

    }

    public void windowClosing(WindowEvent windowEvent) {
        running = false;
    }

    public void windowClosed(WindowEvent windowEvent) {

    }

    public void windowIconified(WindowEvent windowEvent) {

    }

    public void windowDeiconified(WindowEvent windowEvent) {

    }

    public void windowActivated(WindowEvent windowEvent) {

    }

    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
