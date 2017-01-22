package com.fossgalaxy.games.ggj2017;

import com.fossgalaxy.games.ggj2017.world.GameWorld;

import java.awt.*;

/**
 * Created by webpigeon on 22/01/17.
 */
public class GameOverScene implements Scene {
    private App app;
    private GameWorld world;

    public GameOverScene(GameWorld world) {
        this.world = world;
    }

    @Override
    public void onCreate(App app) {
        this.app = app;
    }

    @Override
    public void onActive() {
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 800, 800);

        graphics.setColor(Color.WHITE);
        graphics.drawString("Game Over", 100, 100);
        graphics.drawString("You scored: "+world.getShip().getScore(), 100, 150);
    }
}
