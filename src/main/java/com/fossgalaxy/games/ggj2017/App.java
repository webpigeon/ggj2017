package com.fossgalaxy.games.ggj2017;

import com.fossgalaxy.games.ggj2017.controllers.ShipController;
import com.fossgalaxy.games.ggj2017.controllers.WindController;
import com.fossgalaxy.games.ggj2017.world.GameWorld;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App implements Runnable, WindowListener {
    private final GameWorld world;

    private Scene activeScene;
    private final Frame frame;
    private final Canvas canvas;
    private boolean running = true;

    private final Map<String, Scene> scenes;

    public App() {
        this.frame = new Frame();
        frame.addWindowListener(this);
        this.canvas = buildView(frame);
        this.world = new GameWorld();
        canvas.addMouseListener(new WindController(this.world));
        canvas.addKeyListener(new ShipController(this.world));

        canvas.setFocusable(true);
        canvas.requestFocus();

        this.scenes = new HashMap<>();
        this.activeScene = null;

        addScene("title", new TitleScene());
        addScene("gameover", new GameOverScene(world));
        addScene("world", world);

        setScene("title");
    }

    private void addScene(String name, Scene scene) {
        scenes.put(name, scene);
        scene.onCreate(this);
    }

    public void setScene(String name){
        activeScene = scenes.get(name);
        activeScene.onActive();
    }

    public static void main( String[] args ) throws InterruptedException {
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

                activeScene.update();

                BufferStrategy bs = canvas.getBufferStrategy();
                Graphics2D g = (Graphics2D)bs.getDrawGraphics();

                g.setColor(new Color(99, 197, 207));
                //g.drawRect(0, 0, 10, 10);
                //g.scale(2, 2);

                activeScene.render(g);
//                world.debugRender(g);

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
