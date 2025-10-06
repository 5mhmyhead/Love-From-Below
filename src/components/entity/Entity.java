package components.entity;

import components.rooms.Room;
import utilities.Tile;

import java.awt.*;

public abstract class Entity {

    protected Room room;

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
    public abstract void draw(Graphics2D g2d);

    // RETURNS IF THE ENTITY COLLIDES WITH ANOTHER ENTITY OR ANOTHER RECTANGLE
    boolean checkCollisionWith(Entity other)
    {
        return getRectangle().intersects(other.getRectangle());
    }
    boolean checkCollisionWith(Rectangle otherRectangle)
    {
        return getRectangle().intersects(otherRectangle);
    }

    // RETURN COLLISION BOX FOR THE ENTITY
    public Rectangle getRectangle() {
        return new Rectangle(x - width / 2, y - width / 2, width, height);
    }

    // HANDLES COLLISION WITH TILE
    protected boolean handleTileCollisions() {

        boolean collisionX = false;
        boolean collisionY = false;

        if(checkCollisionWithTileMap(x + velX, y)) {
            collisionX = true;
        }

        if(checkCollisionWithTileMap(x, y + velY)) {
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

    protected boolean checkCollisionWithTileMap(double newX, double newY) {

        boolean collisionFlag = false;

        int leftColumn = (int) Math.round(newX - width / 2) / room.getWidthOfTile();
        int rightColumn = (int) Math.round(newX + width / 2) / room.getWidthOfTile();
        int topRow = (int) Math.round(newY - height / 2) / room.getHeightOfTile();
        int bottomRow = (int) Math.round(newY + height / 2) / room.getHeightOfTile();

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

                    Rectangle thisRectangle = new Rectangle((int) Math.round(newX) - width / 2,
                            (int) Math.round(newY) - height / 2, width, height);

                    if(tile.hasCollision() && thisRectangle.intersects(tileRectangle)) {
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
    protected int alignToGrid(double value, int alignTo) {
        // CHECK HOW MUCH OF VALUE IS
        int extra = (int) Math.round(value) % alignTo;
        // FIND THE HALFWAY MARK OF THE OFFSET
        int halfway = (alignTo - 1) / 2;

        // EITHER PUSHES IT FORWARD TO THE NEXT MARK OR PREVIOUS MARK
        return (extra > halfway) ? alignTo - extra : -extra;
    }

    public double getX() { return x; }
    public double getY() { return y; }

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
    void drawDebug(Graphics2D g2)
    {
        g2.setColor(new Color(255, 0, 0, 100));

        g2.fillRect((int) Math.round(x - width / 2),
                (int) Math.round(y - width / 2), width, height);

        g2.setColor(new Color(0, 0, 255, 100));
    }
}
