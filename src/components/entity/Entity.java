package components.entity;

import components.objects.WorldObject;
import components.world.rooms.Room;
import components.world.rooms.RoomMetadata;
import utilities.Tile;

import java.awt.*;
import java.util.ArrayList;

public abstract class Entity {

    protected Room room;
    protected RoomMetadata roomMetadata;

    protected int x;
    protected int y;

    protected int width;
    protected int height;

    protected int velX;
    protected int velY;

    protected int drawX;
    protected int drawY;

    protected int moveSpeed;

    protected int health;
    protected int invincibilityFrames;
    protected int knockbackDistance;

    protected Direction direction;
    protected String state;

    protected Rectangle bounds;

    protected boolean destroyFlag;

    // UPDATE AND DRAW FUNCTIONS FOR ENTITY
    public abstract void update();
    public abstract void draw(Graphics2D g2);

    // RETURNS IF THE ENTITY COLLIDES WITH ANOTHER ENTITY, WORLD OBJECT OR ANOTHER RECTANGLE
    public boolean checkCollisionWith(Entity other) { return getBounds().intersects(other.getBounds()); }
    public boolean checkCollisionWith(WorldObject other) { return getBounds().intersects(other.getBounds()); }
    public boolean checkCollisionWith(Rectangle otherRectangle) { return getBounds().intersects(otherRectangle); }

    public void setBounds(int x, int y, int width, int height) { bounds = new Rectangle(x, y, width, height); }
    public Rectangle getBounds() { return bounds; }

    protected void handleCollisions() {

        boolean collisionY = checkCollisions(0, velY);
        boolean collisionX = checkCollisions(velX, 0);

        if(collisionX && (direction == Direction.RIGHT || direction == Direction.LEFT)) x -= velX;
        if(collisionY && (direction == Direction.UP || direction == Direction.DOWN)) y -= velY;
    }

    protected boolean checkCollisions(int checkX, int checkY) {

        boolean collisionFlag = false;

        // CHECKS TILE COLLISIONS
        // FINDS THE TILES THAT ARE CLOSEST TO THE ENTITY, GREATLY REDUCES THE AMOUNT OF COLLISION CHECKS
        int leftColumn = (x + checkX) / room.getWidthOfTile();
        int rightColumn = (x + checkX + width) / room.getWidthOfTile();
        int topRow = (y + checkY) / room.getHeightOfTile();
        int bottomRow = (y + checkY + height) / room.getHeightOfTile();

        if(leftColumn < 0) leftColumn = 0;
        if(topRow < 0) topRow = 0;

        if(rightColumn > room.getNumOfColumns() - 1) rightColumn = room.getNumOfColumns() - 1;
        if(bottomRow > room.getNumOfRows() - 1) bottomRow = room.getNumOfRows() - 1;

        for(int i = leftColumn; i <= rightColumn; i++) {

            for(int j = topRow; j <= bottomRow; j++) {

                Tile tile = room.getTile(i, j);

                if(tile != null) {

                    Rectangle tileRectangle = new Rectangle(i * room.getWidthOfTile(),
                            j * room.getHeightOfTile(), room.getWidthOfTile(), room.getHeightOfTile());

                    if(tile.hasTileCollision() && getBounds().intersects(tileRectangle)) collisionFlag = true;
                }
            }
        }

        // CHECKS OBJECT AND ENTITY COLLISIONS
        ArrayList<WorldObject> worldObjects = roomMetadata.getWorldObjects();
        ArrayList<Entity> worldNPCS = roomMetadata.getWorldNPCS();

        for(WorldObject object : worldObjects)
            if(object.isCollidable() && checkCollisionWith(object))
                collisionFlag = true;

        for(Entity npc : worldNPCS)
            if(checkCollisionWith(npc))
                collisionFlag = true;

        return collisionFlag;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setCoordinates(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {

        this.width = width;
        this.height = height;
    }

    public Direction getDirection() { return direction; }
    public String getState() { return state; }

    // DEBUG TO DRAW BOUNDS OF ENTITY
    public void drawDebug(Graphics2D g2) {

        g2.setColor(new Color(255, 0, 0));
        g2.fill(getBounds());

        g2.setColor(new Color(255, 255, 0, 60));
        switch(direction) {

            case UP: g2.fillRect(x + 8, y, 32, 16); break;
            case DOWN: g2.fillRect(x + 8, y + 48, 32, 16); break;
            case LEFT: g2.fillRect(x - 8, y + 16, 16, 32); break;
            case RIGHT: g2.fillRect(x + 40, y + 16, 16, 32); break;
        }
    }
}
