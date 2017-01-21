package com.fossgalaxy.games.ggj2017.sprites;

import java.awt.image.BufferedImage;

/**
 * Created by newowner on 21/01/2017.
 */
public class SpriteManager {

    private static BufferedImage[] getSet(BufferedImage image, int[] arguments){
        int rows = arguments[0];
        int columns = arguments[1];
        int width = arguments[2];
        int height = arguments[3];
        int xOffset = arguments[4];
        int yOffset = arguments[5];

        BufferedImage[] sprites = new BufferedImage[rows * columns];

        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; columns++){
                sprites[(row * columns) + column] = image.getSubimage(
                        xOffset + (column * width),
                        yOffset + (row * height),
                        width,
                        height
                );
            }
        }
        return sprites;
    }
}
