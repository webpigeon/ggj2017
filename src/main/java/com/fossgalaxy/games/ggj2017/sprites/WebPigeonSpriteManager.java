package com.fossgalaxy.games.ggj2017.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by webpigeon on 22/01/17.
 */
public class WebPigeonSpriteManager {
    public static final WebPigeonSpriteManager instance = new WebPigeonSpriteManager();

    private Map<String, BufferedImage> sprites = new HashMap<>();

    public BufferedImage[][] cut(BufferedImage src, int w, int h) {

        int cols = src.getWidth() / w;
        int rows = src.getHeight() / h;
        BufferedImage[][] parts = new BufferedImage[cols][rows];

        for (int col=0; col<cols; col++) {
            for (int row=0; row<rows; row++) {
                parts[col][row] = src.getSubimage(col * w, row * h, w, h);
            }
        }

        return parts;
    }

    public BufferedImage load(String name) {
        try {
            ClassLoader cl = WebPigeonSpriteManager.class.getClassLoader();
            BufferedImage image = ImageIO.read(cl.getResourceAsStream(name + ".png"));
            return image;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public BufferedImage get(String name) {
        return sprites.computeIfAbsent(name, this::load);
    }
}
