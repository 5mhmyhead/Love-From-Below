package entities;

import utilities.Spritesheet;

import java.awt.image.BufferedImage;

public class Entity {

    protected int x;
    protected int y;

    protected int width;
    protected int height;

    protected String direction;
    protected int speed;

    // TEMPORARY
    public Spritesheet spritesheet;
    public BufferedImage sprite;
}
