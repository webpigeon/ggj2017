package com.fossgalaxy.games.ggj2017;

import java.awt.*;

/**
 * Created by webpigeon on 22/01/17.
 */
public interface Scene {

    void onCreate(App app);
    void update();
    void render(Graphics2D graphics);

    default void onActive(){

    }
}
