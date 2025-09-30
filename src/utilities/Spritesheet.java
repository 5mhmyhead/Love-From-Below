package utilities;

import core.GamePanel;

import java.awt.image.BufferedImage;

public class Spritesheet {

    private final BufferedImage image;

    public Spritesheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int row, int col, int width, int height) {
        return image.getSubimage(
                (col * GamePanel.tileSize) - GamePanel.tileSize,
                (row * GamePanel.tileSize) - GamePanel.tileSize,
                   width, height
        );
    }
}
