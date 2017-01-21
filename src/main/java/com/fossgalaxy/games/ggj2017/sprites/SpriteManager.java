package com.fossgalaxy.games.ggj2017.sprites;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by newowner on 21/01/2017.
 */
public class SpriteManager {

    private static BufferedImage[] getSet(BufferedImage image, int[] arguments) {
        int rows = arguments[0];
        int columns = arguments[1];
        int width = arguments[2];
        int height = arguments[3];
        int xOffset = arguments[4];
        int yOffset = arguments[5];
        int xIndOffset = arguments[6];
        int yIndOffset = arguments[7];

        System.out.println(xOffset + ":" + yOffset + ":" + xIndOffset + ":" + yIndOffset);

        BufferedImage[] sprites = new BufferedImage[rows * columns];
        System.out.println("Loading: " + sprites.length + " Images");

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.println("Reading: " + row + ":" + column);

                int xStart = xOffset + (column * width) + (column * xIndOffset);
                int yStart = yOffset + (row * height) + (row * yIndOffset);

                System.out.println(xStart + ":" + yStart);
                sprites[(row * columns) + column] = image.getSubimage(
                        xStart,
                        yStart,
                        width,
                        height
                );
            }
        }
        return sprites;
    }

    private static BufferedImage[][] stitchQuads(BufferedImage[] images, int width, int height) {
        BufferedImage[][] output = new BufferedImage[width][height];
        int i = 0;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                System.out.println("i: " + i + "x: " + x + "y: " + y);
                output[x][y] = stitchQuad(new BufferedImage[]{
                        images[(x * 2)], images[(x * 2) + 1],
                        images[(y * 2)], images[(y * 2) + 1]
                });
            }
        }
        return output;
    }

    private static BufferedImage stitchQuad(BufferedImage[] images) {
        int width = images[0].getWidth() * 2;
        int height = images[0].getHeight() * 2;
        BufferedImage output = new BufferedImage(
                width, height,
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics graphics = output.createGraphics();

        graphics.drawImage(images[0], 0, 0, null);
        graphics.drawImage(images[1], width / 2, 0, null);
        graphics.drawImage(images[2], 0, height / 2, null);
        graphics.drawImage(images[3], width / 2, height / 2, null);
        graphics.dispose();

        return output;
    }

    /**
     * @param image
     * @return [direction][Animation Index]
     */
    private static BufferedImage[][] getShips(BufferedImage image) {
        return null;
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println("Start");

        BufferedImage all = ImageIO.read(SpriteManager.class.getResourceAsStream("/spritesheet.png"));
        System.out.println("Read the main image");
        BufferedImage[] quad = getSet(all, new int[]{2, 2, 16, 16, 221, 0, 1, 1});
        System.out.println("Got the quad");
        BufferedImage ship = stitchQuad(quad);
        System.out.println("Stitched them");

        BufferedImage[] set = getSet(all, new int[]{12, 12, 16, 16, 221, 0, 1, 1});
        BufferedImage[][] setStitched = stitchQuads(set, 6, 6);
        frame.add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.scale(10, 10);
                int x = 0;
                for (BufferedImage[] imageRow : setStitched) {
                    int y = 0;
                    for (BufferedImage image : imageRow) {
                        if (image != null) {
                            g2.drawImage(image, x, y, null);
                            x += image.getWidth();
                        } else {
                            System.out.println("Was Null");
                        }
                    }
                    y += imageRow[0].getHeight();
                }
                g2.scale(0.1, 0.1);
            }
        });

        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
