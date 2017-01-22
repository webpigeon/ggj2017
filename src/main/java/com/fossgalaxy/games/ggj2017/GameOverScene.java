package com.fossgalaxy.games.ggj2017;

import java.awt.*;

/**
 * Created by webpigeon on 22/01/17.
 */
public class GameOverScene implements Scene {
    private App app;

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
        graphics.fillRect(0, 0, 800, 600);

        graphics.setColor(Color.WHITE);
        graphics.drawString("Game Over ", 100, 100);
    }
}
