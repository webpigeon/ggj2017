package com.fossgalaxy.games.ggj2017;

import java.awt.*;

/**
 * Created by webpigeon on 22/01/17.
 */
public class TitleScene implements Scene {
    private App app;
    private int countdown = 0;

    @Override
    public void onCreate(App app) {
        this.app = app;
    }

    @Override
    public void onActive() {
        countdown = 0;
    }

    @Override
    public void update() {
        if (countdown == 120) {
            app.setScene("world");
            return;
        }
        countdown++;
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 800, 800);



        graphics.setColor(Color.GREEN);
        graphics.fillRect(200, 500, (int)((countdown/120f) * 400), 5);
    }
}
