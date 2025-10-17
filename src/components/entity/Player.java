package components.entity;

import components.objects.Boots;
import components.objects.NormalChest;
import components.objects.WorldObject;
import components.world.rooms.RoomMetadata;
import components.world.World;
import core.GamePanel;
import utilities.Animation;
import utilities.GameData;
import utilities.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {

    // THE CURRENT WORLD OR MAP THE PLAYER IS IN
    private final World world;

    // PLAYER INPUTS
    private boolean inputLeft, inputUp, inputRight,
                    inputDown, interact, sprint;

    // PLAYER ANIMATIONS
    private Animation walkUp, walkDown, walkLeft, walkRight,
                    runUp, runDown, runLeft, runRight;

    private int transitionAmountX, transitionAmountY;   // HOW FAR LINK HAS MOVED IN THE TRANSITION
    private int transitionVelX, transitionVelY;         // HOW FAST LINK IS MOVING FOR THE TRANSITION

    // MISCELLANEOUS VARIABLES FOR KEYDOWN VALUES AND ANIMATION DELAY
    private int idleDelay = 0;                          // COUNT TO SMOOTHEN TRANSITION BETWEEN WALKING TO IDLE STATE
    private boolean interactPrevPressed = false;        // BOOLEAN FOR INTERACT KEY TO REGISTER ONLY ONCE

    // WORLD OBJECTS AND ENTITIES
    ArrayList<WorldObject> worldObjects;
    ArrayList<Entity> worldNPCS;

    public Player(World world, RoomMetadata metadata) {

        this.world = world;
        this.roomMetadata = metadata;
        this.room = world.getCurrentRoom();

        setDefaultValues();
    }

    private void setDefaultValues() {

        setCoordinates(200, 200);
        setSize(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        setBounds(x + 8, y + 16, width - 16, height - 16);

        drawX = x;
        drawY = y;
        moveSpeed = 3;

        // PLAYER ANIMATIONS
        walkUp = new Animation(10, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_UP), width, height);
        walkDown = new Animation(10, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_DOWN), width, height);
        walkLeft = new Animation(10, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_WALK_LEFT), width, height);
        walkRight = new Animation(10, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_WALK_RIGHT), width, height);

        runUp = new Animation(7, true, Images.PlayerAssets.PLAYER_UP, width, height);
        runDown = new Animation(7, true, Images.PlayerAssets.PLAYER_DOWN, width, height);
        runLeft = new Animation(7, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_RUN_LEFT), width, height);
        runRight = new Animation(7, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_RUN_RIGHT), width, height);

        direction = Direction.DOWN;
        state = "IDLE";
    }

    // UPDATES PLAYER POSITION, ANIMATION, ETC
    public void update() {
        // SETS UP THE CURRENT ROOM
        this.room = world.getCurrentRoom();
        this.roomMetadata = world.getRoomMetadata();

        switch(state) {
            case "IDLE":
                velX = 0;
                velY = 0;

                updatePlayerState();
                break;

            case "DIALOGUE":
                velX = 0;
                velY = 0;

                break;

            case "UP":
                velY = -moveSpeed;
                direction = Direction.UP;

                if(sprint) runUp.update();
                else walkUp.update();

                y += velY;
                updatePlayerState();
                break;

            case "DOWN":
                velY = moveSpeed;
                direction = Direction.DOWN;

                if(sprint) runDown.update();
                else walkDown.update();

                y += velY;
                updatePlayerState();
                break;

            case "LEFT":
                velX = -moveSpeed;
                direction = Direction.LEFT;

                if(sprint) runLeft.update();
                else walkLeft.update();

                x += velX;
                updatePlayerState();
                break;

            case "RIGHT":
                velX = moveSpeed;
                direction = Direction.RIGHT;

                if(sprint) runRight.update();
                else walkRight.update();

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

        // CHECK COLLISIONS WITH PLAYER AND TILEMAP
        if(!state.equals("TRANSITION")) {
            // UPDATE THE COLLISION BOX FOR THE PLAYER
            setBounds(x + 8, y + 16, width - 16, height - 16);

            handleCollisions();
            handleNonInteractables();       // HANDLES COLLISIONS THAT DO NOT NEED THE INTERACT KEY

            // INTERACT PREVIOUS PRESSED ENSURES THAT THE FUNCTION UPDATES ONLY ONCE EVEN WHEN HELD
            if(!interactPrevPressed && !interact) interactPrevPressed = true;
            if(interactPrevPressed && interact) {

                interactPrevPressed = false;
                handleInteractables();      // HANDLES COLLISIONS THAT REQUIRES INTERACTING
            }
        }
    }

    // UPDATE THE PLAYER STATE VARIABLE
    private void updatePlayerState() {

            if(inputUp) state = "UP";
            if(inputDown) state = "DOWN";
            if(inputLeft) state = "LEFT";
            if(inputRight) state = "RIGHT";

            if(sprint) moveSpeed = 5;
            else moveSpeed = 3;

            if(!(inputUp || inputDown || inputLeft || inputRight)) state = "IDLE";
            if(transitionVelX != 0 || transitionVelY != 0) 	state = "TRANSITION";
    }

    // UPDATES PLAYER INPUT VARIABLES
    public void setInput(int key, boolean bool) {
        // MOVEMENT
        if(key == KeyEvent.VK_D) inputRight = bool;
        if(key == KeyEvent.VK_A) inputLeft = bool;
        if(key == KeyEvent.VK_W) inputUp = bool;
        if(key == KeyEvent.VK_S) inputDown = bool;
        if(key == KeyEvent.VK_SHIFT && GameData.hasBoots) sprint = bool;

        // INTERACT
        if(key == KeyEvent.VK_E) interact = bool;
    }

    // RETURN ITEM RANGE WHEN PRESSING INTERACT, DEPENDING ON THE DIRECTION OF THE PLAYER
    private Rectangle getItemRange() {

        return switch (direction) {

            case UP -> new Rectangle(x + 8, y, 32, 16);
            case DOWN -> new Rectangle(x + 8, y + 48, 32, 16);
            case LEFT -> new Rectangle(x - 8, y + 16, 16, 32);
            case RIGHT -> new Rectangle(x + 40, y + 16, 16, 32);
        };
    }

    private void handleInteractables() {

        worldObjects = room.getWorldObjects();
        worldNPCS = room.getWorldNPCS();

        for (WorldObject worldObject : worldObjects)
            if (worldObject instanceof NormalChest && this.getItemRange().intersects(worldObject.getBounds()))
                worldObject.update();

        for(Entity npc : worldNPCS) {

            if(this.getItemRange().intersects(npc.getBounds())) {

                npc.update();
                if(npc.getState().equals("DIALOGUE")) state = "DIALOGUE";
                if(!npc.getState().equals("DIALOGUE")) state = "IDLE";
            }
        }
    }

    private void handleNonInteractables() {

        worldObjects = room.getWorldObjects();

        for (WorldObject worldObject : worldObjects)
            if (checkCollisionWith(worldObject.getBounds()) && worldObject instanceof Boots)
                worldObject.update();
    }

    public void draw(Graphics2D g2) {
        //Set integer forms of the current x/y for drawing
        drawX = x;
        drawY = y;

        switch(state) {

            case "IDLE", "TRANSITION", "DIALOGUE":

                idleDelay++;

                if(idleDelay < 10) {

                    switch (direction) {

                        case UP: walkUp.draw(g2, drawX, drawY, width, height); break;
                        case RIGHT: walkRight.draw(g2, drawX, drawY, width, height); break;
                        case DOWN: walkDown.draw(g2, drawX, drawY, width, height); break;
                        case LEFT: walkLeft.draw(g2, drawX, drawY, width, height); break;
                        default: break;
                    }

                } else {

                    switch (direction) {

                        case UP: walkUp.drawFirst(g2, drawX, drawY, width, height); break;
                        case RIGHT: walkRight.drawFirst(g2, drawX, drawY, width, height); break;
                        case DOWN: walkDown.drawFirst(g2, drawX, drawY, width, height); break;
                        case LEFT: walkLeft.drawFirst(g2, drawX, drawY, width, height); break;
                        default: break;
                    }

                }

                break;

            case "UP":

                idleDelay = 0;
                if(sprint) runUp.draw(g2, drawX, drawY, width, height);
                else walkUp.draw(g2, drawX, drawY, width, height);
                break;

            case "DOWN":

                idleDelay = 0;
                if(sprint) runDown.draw(g2, drawX, drawY, width, height);
                else walkDown.draw(g2, drawX, drawY, width, height);
                break;

            case "RIGHT":

                idleDelay = 0;
                if(sprint) runRight.draw(g2, drawX, drawY, width, height);
                else walkRight.draw(g2, drawX, drawY, width, height);
                break;

            case "LEFT":

                idleDelay = 0;
                if(sprint) runLeft.draw(g2, drawX, drawY, width, height);
                else walkLeft.draw(g2, drawX, drawY, width, height);
                break;

            default:

                g2.setColor(Color.RED);
                g2.drawRect(drawX, drawY, width, height);
                break;
        }
    }

    public void setTransitionVector(int transitionVelX, int transitionVelY) {

        this.transitionVelX = transitionVelX;
        this.transitionVelY = transitionVelY;
    }

    public boolean isTransitioning() { return state.equals("TRANSITION") || world.isTransitioning(); }

    public void drawInteractDebug(Graphics2D g2) {

        g2.setColor(new Color(255, 255, 0, 60));
        switch(direction) {

            case UP: g2.fillRect(x + 8, y, 32, 16); break;
            case DOWN: g2.fillRect(x + 8, y + 48, 32, 16); break;
            case LEFT: g2.fillRect(x - 8, y + 16, 16, 32); break;
            case RIGHT: g2.fillRect(x + 40, y + 16, 16, 32); break;
        }
    }
}
