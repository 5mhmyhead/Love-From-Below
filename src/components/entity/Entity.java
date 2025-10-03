package components.entity;

import components.rooms.Room;

import java.awt.*;

public abstract class Entity {

    protected Room room;

    protected double x;
    protected double y;

    protected double velX;
    protected double velY;

    protected double moveSpeed;

    protected int drawX;
    protected int drawY;

    protected int width;
    protected int height;

    protected Direction direction;
    protected String state;

    // UPDATE AND DRAW FUNCTIONS FOR ENTITY
    public abstract void update();
    public abstract void draw(Graphics2D g2d);

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
    public double getY()
    {
        return y;
    }

    public void setCoordinates(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public Direction getDirection() { return direction; }
    public String getState() { return state; }
}
