package com.fossgalaxy.games.ggj2017;

import com.fossgalaxy.games.ggj2017.world.GameWorld;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

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
        frame.setPreferredSize(new Dimension(800, 600));
        Canvas canvas = new Canvas();

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        return canvas;
    }


    public void run() {
        try {
            while (running) {

                world.update();

                BufferStrategy bs = canvas.getBufferStrategy();
                Graphics2D g = (Graphics2D)bs.getDrawGraphics();

                g.setColor(Color.BLACK);
                //g.drawRect(0, 0, 10, 10);
                g.scale(25, 25);


                world.debugRender(g);

                g.dispose();

                bs.show();

                Thread.sleep(1000 / 50);
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
