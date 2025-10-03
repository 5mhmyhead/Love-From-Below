package components.entity;

import components.world.World;
import core.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    // THE CURRENT WORLD OR MAP THE PLAYER IS IN
    private final World world;

    // PLAYER INPUTS
    private boolean inputLeft, inputUp, inputRight,
                    inputDown;

    private int transitionAmountX, transitionAmountY;   // HOW FAR LINK HAS MOVED IN THE TRANSITION
    private int transitionVelX, transitionVelY;         // HOW FAST LINK IS MOVING FOR THE TRANSITION

    // CONSTRUCTOR
    public Player(World world) {

        this.world = world;
        this.room = world.getCurrentRoom();

        setDefaultValues();
    }

    private void setDefaultValues() {

        x = 384;
        y = 288;

        drawX = (int) Math.round(x);
        drawY = (int) Math.round(y);

        moveSpeed = 3;

        width = GamePanel.TILE_SIZE;
        height = GamePanel.TILE_SIZE;

        direction = Direction.DOWN;
        state = "IDLE";
    }

    // UPDATES PLAYER POSITION, ANIMATION, ETC
    public void update() {

        this.room = world.getCurrentRoom();

        switch(state) {
            case "IDLE":
                velX = 0;
                velY = 0;

                updatePlayerState();
                break;

            case "UP":
                velX = alignToGrid(x, 8);
                velY = -moveSpeed;
                direction = Direction.UP;

                y += velY;

                updatePlayerState();
                break;

            case "DOWN":
                velX = alignToGrid(x, 8);
                velY = moveSpeed;
                direction = Direction.DOWN;

                y += velY;

                updatePlayerState();
                break;

            case "LEFT":
                velX = -moveSpeed;
                velY = alignToGrid(y, 8);
                direction = Direction.LEFT;

                x += velX;

                updatePlayerState();
                break;

            case "RIGHT":
                velX = moveSpeed;
                velY = alignToGrid(y, 8);
                direction = Direction.RIGHT;

                x += velX;

                updatePlayerState();
                break;

            case "TRANSITION":

                velX = 0;
                velY = 0;

                // MOVE PLAYER WITH THE TRANSITION
                x += transitionVelX;
                y += transitionVelY;

                // CHECK HOW FAR PLAYER HAS MOVED
                transitionAmountX += transitionVelX;
                transitionAmountY += transitionVelY;

                // MOVE THE PLAYER UNTIL HE IS 20 PIXELS AWAY FROM THE EDGE OF ROOM
                if(Math.abs(transitionAmountX) >= room.getRoomWidth() - 20) transitionVelX = 0;
                if(Math.abs(transitionAmountY) >= room.getRoomHeight() - 20) transitionVelY = 0;

                if(transitionVelX == 0 && transitionVelY == 0) {
                    // RESET AMOUNT
                    transitionAmountX = 0;
                    transitionAmountY = 0;

                    // RESET PLAYER TO IDLE
                    if(!world.isTransitioning()) state = "IDLE";
                }
                break;

            default:
                System.out.println(state);
                break;
        }
    }

    // UPDATE THE PLAYER STATE VARIABLE
    private void updatePlayerState() {

        if(inputUp) state = "UP";
        if(inputDown) state = "DOWN";
        if(inputLeft) state = "LEFT";
        if(inputRight) state = "RIGHT";

        if(!(inputUp || inputDown || inputLeft || inputRight))
            state = "IDLE";

        if(transitionVelX != 0 || transitionVelY != 0) 	state = "TRANSITION";
    }

    // UPDATES PLAYER INPUT VARIABLES
    public void setInput(int key, boolean bool) {

        if(key == KeyEvent.VK_D) inputRight = bool;
        if(key == KeyEvent.VK_A) inputLeft = bool;
        if(key == KeyEvent.VK_W) inputUp = bool;
        if(key == KeyEvent.VK_S) inputDown = bool;
    }

    public void draw(Graphics2D g2) {
        //Set integer forms of the current x/y for drawing
        drawX = (int) Math.round(x) - width / 2;
        drawY = (int) Math.round(y) - height / 2;

        g2.setColor(Color.white);
        g2.fillRect(drawX, drawY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }

    public void setTransitionVector(int transitionVelX, int transitionVelY) {

        this.transitionVelX = transitionVelX;
        this.transitionVelY = transitionVelY;
    }

    public boolean isTransitioning() {
        return state.equals("TRANSITION") || world.isTransitioning();
    }
}
