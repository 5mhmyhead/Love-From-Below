package components.entity;

import components.objects.WorldObject;
import components.rooms.Room;
import components.rooms.RoomMetadata;
import utilities.Tile;

import java.awt.*;
import java.util.ArrayList;

public abstract class Entity {

    protected Room room;
    protected RoomMetadata roomMetadata;

    protected int x;
    protected int y;

    protected int velX;
    protected int velY;

    protected int drawX;
    protected int drawY;

    protected int width;
    protected int height;

    protected int moveSpeed;

    protected Direction direction;
    protected String state;

    // UPDATE AND DRAW FUNCTIONS FOR ENTITY
    public abstract void update();
    public abstract void draw(Graphics2D g2);

    // RETURNS IF THE ENTITY COLLIDES WITH ANOTHER ENTITY OR ANOTHER RECTANGLE
    public boolean checkCollisionWith(Entity other)
    {
        return getBounds().intersects(other.getBounds());
    }
    public boolean checkCollisionWith(Rectangle otherRectangle)
    {
        return getBounds().intersects(otherRectangle);
    }

    // RETURN COLLISION BOX FOR THE ENTITY
    public Rectangle getBounds() {
        return new Rectangle(x - width / 2, y - width / 2, width, height);
    }

    // RETURN ITEM RANGE FOR THE ENTITY, DEPENDING ON THE DIRECTION OF THE PLAYER
    public Rectangle getItemRange() {

        return switch (direction) {

            case UP -> new Rectangle(x - width / 2, y - height / 2, width, height / 4);
            case DOWN -> new Rectangle(x - width / 2, y + height / 2, width, height / 4);
            case LEFT -> new Rectangle(x - width / 2 - width / 3, y - height / 2, width / 4, height);
            case RIGHT -> new Rectangle(x + width / 2, y - height / 2, width / 4, height);
        };
    }

    // HANDLES COLLISION WITH TILE
    protected boolean handleTileCollisions() {

        boolean collisionX = false;
        boolean collisionY = false;

        if(checkCollisionWithTileMap(x + velX, y) ||
                checkCollisionWithObjects(x + velX, y)) {
            collisionX = true;
        }

        if(checkCollisionWithTileMap(x, y + velY) ||
                checkCollisionWithObjects(x, y + velY)) {
            collisionY = true;
        }

        // UPDATES THE PLAYERS X AND Y TO MOVE THE PLAYER
        x += velX;
        y += velY;

        if(collisionX) {

            x = (x / 8) * 8;
            if(sign(velX) == -1) x += 8;   // CORRECT ROUNDING

            velX = 0;
            velY = 0;
        }

        if(collisionY) {

            y = (y / 8) * 8;
            if(sign(velY) == -1) y += 8;   // CORRECT ROUNDING

            velX = 0;
            velY = 0;
        }

        return collisionX || collisionY;
    }

    protected boolean checkCollisionWithObjects(int newX, int newY) {

        ArrayList<WorldObject> worldObjects = roomMetadata.getWorldObjects();

        for(WorldObject object : worldObjects) {

            Rectangle objectRectangle = new Rectangle(object.getX(), object.getY(),
                    object.getWidth(), object.getHeight() / 2);

            Rectangle thisRectangle = new Rectangle(newX - width / 2, newY - height / 2,
                    width, height);

            if(object.isCollidable() && thisRectangle.intersects(objectRectangle)) {
                return true;
            }
        }

        return false;
    }

    protected boolean checkCollisionWithTileMap(int newX, int newY) {

        boolean collisionFlag = false;

        int leftColumn = (newX - width / 2) / room.getWidthOfTile();
        int rightColumn = (newX + width / 2) / room.getWidthOfTile();
        int topRow = (newY - height / 2) / room.getHeightOfTile();
        int bottomRow = (newY + height / 2) / room.getHeightOfTile();

        if(leftColumn < 0) leftColumn = 0;
        if(topRow < 0) topRow = 0;

        if(rightColumn > room.getNumOfColumns() - 1) rightColumn = room.getNumOfColumns() - 1;
        if(bottomRow > room.getNumOfRows() - 1) bottomRow = room.getNumOfRows() - 1;

        for(int i = leftColumn; i <= rightColumn; i++) {

            for(int j = topRow; j <= bottomRow; j++) {

                Tile tile = room.getTile(i, j);

                if(tile != null) {

                    Rectangle tileRectangle = new Rectangle(i * room.getWidthOfTile(),
                            j * room.getHeightOfTile(), room.getWidthOfTile(),
                            room.getHeightOfTile() / 2);

                    Rectangle thisRectangle = new Rectangle(newX - width / 2, newY - height / 2,
                            width, height);

                    if(tile.hasTileCollision() && thisRectangle.intersects(tileRectangle)) {
                        collisionFlag = true;
                    }
                }
            }
        }

        return  collisionFlag;
    }

    // RETURNS A VALUE THAT IS ALIGNED TO THE NEAREST PROVIDED INT
    // value   - VALUE TO BE ALIGNED
    // alignTo - DISTANCE FOR THE VALUE TO BE ALIGNED TO
    protected int alignToGrid(double value) {
        // CHECK HOW MUCH OF VALUE IS
        int extra = (int) Math.round(value) % 8;
        // FIND THE HALFWAY MARK OF THE OFFSET
        int halfway = (8 - 1) / 2;
        // EITHER PUSHES IT FORWARD TO THE NEXT MARK OR PREVIOUS MARK
        return (extra > halfway) ? 8 - extra : -extra;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setCoordinates(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public Direction getDirection() { return direction; }
    public String getState() { return state; }

    // RETURNS SIGN OF DOUBLE: SIMPLE HELPER FUNCTION
    public static int sign(double number) {

        if(number > 0) return 1;
        else if(number < 0) return -1;
        else return 0;
    }

    // DEBUG TO DRAW COLLISIONS OF ENTITIES
    void drawDebug(Graphics2D g2) {
        // DRAW ENTITY BODY
        g2.setColor(new Color(255, 0, 0));
        g2.fillRect(x - width / 2, y - height / 2, width, height);

        // DRAW ENTITY RANGE
        g2.setColor(new Color(255, 255, 0, 60));

        switch(direction) {

            case UP: g2.fillRect(x - width / 2, y - height / 2, width, height / 4); break;
            case DOWN: g2.fillRect(x - width / 2, y + height / 2, width, height / 4); break;
            case LEFT: g2.fillRect(x - width / 2 - width / 4, y - height / 2, width / 4, height); break;
            case RIGHT: g2.fillRect(x + width / 2, y - height / 2, width / 4, height); break;
        }
    }
}
