package components.objects;

import components.rooms.Room;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class WorldObject {

    protected Room room;

    protected int x;
    protected int y;

    protected int width;
    protected int height;

    protected BufferedImage image;
    protected boolean hasCollision;

    public abstract void update();
    public abstract void draw(Graphics2D g2);

    public int getX() { return x; }
    public int getY() { return y; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);
    }

    public boolean isCollidable() { return hasCollision; }
}
