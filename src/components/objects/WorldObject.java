package components.objects;

import components.entity.Player;
import components.world.rooms.Room;
import core.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

// AN OBJECT THAT EXISTS IN THE WORLD (COLLECTIBLES, INTERACTABLES, WARP TILES)
public abstract class WorldObject {

    protected Room room;

    protected int x;
    protected int y;

    protected int width = GamePanel.TILE_SIZE;
    protected int height = GamePanel.TILE_SIZE;

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
