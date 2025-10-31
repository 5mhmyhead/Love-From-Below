package components.entities;

import components.objects.Collectible;
import components.objects.Interactable;
import components.objects.Weapon;
import components.objects.WorldObject;
import components.world.World;
import core.GamePanel;
import utilities.Animation;
import core.ui.GameData;
import utilities.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Player extends Entity {

    // THE CURRENT WORLD OR MAP THE PLAYER IS IN
    private final World world;

    // PLAYER INPUTS
    private boolean inputLeft, inputUp, inputRight,
                    inputDown, interact, sprint,
                    inputAttack;

    // PLAYER ANIMATIONS
    private Animation walkUp, walkDown, walkLeft, walkRight,
                    runUp, runDown, runLeft, runRight,
                    attackUp, attackDown, attackLeft, attackRight;

    private Animation sparkle;

    private Weapon playerWeapon;                        // WEAPON THAT THE PLAYER HAS
    private boolean weaponActive;                       // BOOLEAN FOR HANDLING COLLISIONS
    private int weaponTimer;                            // TIMER FOR THE WEAPON ANIMATION

    private int maxHealth;

    private int transitionAmountX, transitionAmountY;   // HOW FAR LINK HAS MOVED IN THE TRANSITION
    private int transitionVelX, transitionVelY;         // HOW FAST LINK IS MOVING FOR THE TRANSITION

    // MISCELLANEOUS VARIABLES FOR KEYDOWN VALUES AND ANIMATION DELAY
    private int idleDelay = 0;                          // COUNT TO SMOOTHEN TRANSITION BETWEEN WALKING TO IDLE STATE
    private boolean interactPrevPressed = false;        // BOOLEAN FOR INTERACT KEY TO REGISTER ONLY ONCE

    // WORLD OBJECTS AND ENTITIES
    ArrayList<WorldObject> worldObjects;
    ArrayList<NPC> worldNPCS;

    private WorldObject drawObject;                     // THE WORLD OBJECT THAT THE PLAYER JUST GOT
    private int getAnimationTimer;                      // THE TIMER FOR THE GET ITEM ANIMATION

    public Player(World world) {

        this.world = world;
        this.room = world.getCurrentRoom();

        setDefaultValues();
    }

    private void setDefaultValues() {

        // PLAYER ATTRIBUTES
        setCoordinates(200, 200);
        setSize(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        setBounds(x + 8, y + 16, width - 16, height - 16);

        drawX = x;
        drawY = y;
        moveSpeed = 3;

        knockbackDistance = 0;
        getAnimationTimer = 0;

        health = 6;
        maxHealth = 6;

        // WALK ANIMATIONS
        walkUp = new Animation(10, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_UP), width, height);
        walkDown = new Animation(10, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_DOWN), width, height);
        walkLeft = new Animation(10, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_WALK_LEFT), width, height);
        walkRight = new Animation(10, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_WALK_RIGHT), width, height);

        runUp = new Animation(7, true, Images.PlayerAssets.PLAYER_UP, width, height);
        runDown = new Animation(7, true, Images.PlayerAssets.PLAYER_DOWN, width, height);
        runLeft = new Animation(7, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_RUN_LEFT), width, height);
        runRight = new Animation(7, true, Objects.requireNonNull(Images.PlayerAssets.PLAYER_RUN_RIGHT), width, height);

        // ATTACK ANIMATIONS
        attackUp = new Animation(4, false, Objects.requireNonNull(Images.PlayerAssets.PLAYER_ATTACK_UP),
                width * 3, height * 3);
        attackDown = new Animation(4, false, Objects.requireNonNull(Images.PlayerAssets.PLAYER_ATTACK_DOWN),
                width * 3, height * 3);
        attackLeft = new Animation(4, false, Objects.requireNonNull(Images.PlayerAssets.PLAYER_ATTACK_LEFT),
                width * 3, height * 3);
        attackRight = new Animation(4, false, Objects.requireNonNull(Images.PlayerAssets.PLAYER_ATTACK_RIGHT),
                width * 3, height * 3);

        // SPARKLE ANIMATION FROM GET ITEM
        sparkle = new Animation(10, true, Objects.requireNonNull(Images.Effects.SPARKLE), width * 2, height * 2);

        direction = Direction.DOWN;
        state = "IDLE";
    }

    // UPDATES PLAYER POSITION, ANIMATION, ETC
    @Override
    public void update() {
        // SETS UP THE CURRENT ROOM
        this.room = world.getCurrentRoom();

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
                direction = Direction.UP;

                if(sprint) runUp.update();
                else walkUp.update();

                velY = -moveSpeed;
                y += velY;
                updatePlayerState();
                break;

            case "DOWN":
                direction = Direction.DOWN;

                if(sprint) runDown.update();
                else walkDown.update();

                velY = moveSpeed;
                y += velY;
                updatePlayerState();
                break;

            case "LEFT":
                direction = Direction.LEFT;

                if(sprint) runLeft.update();
                else walkLeft.update();

                velX = -moveSpeed;
                x += velX;
                updatePlayerState();
                break;

            case "RIGHT":
                direction = Direction.RIGHT;

                if(sprint) runRight.update();
                else walkRight.update();

                velX = moveSpeed;
                x += velX;
                updatePlayerState();
                break;

            case "ATTACK":
                velX = 0;
                velY = 0;

                if(weaponTimer == 0) {

                    weaponActive = false;
                    weaponTimer = 25;
                }
                else weaponTimer--;

                if(weaponTimer == playerWeapon.startUp) weaponActive = true;
                if(weaponTimer == playerWeapon.windDown) weaponActive = false;

                // UPDATE THE ANIMATION DEPENDING ON THE DIRECTION OF THE PLAYER
                switch(direction) {

                    case UP:
                        attackUp.update();
                        if(attackUp.hasEnded()) attackUp.setIndex(4);
                        break;
                    case DOWN:
                        attackDown.update();
                        if(attackDown.hasEnded()) attackDown.setIndex(4);
                        break;
                    case LEFT:
                        attackLeft.update();
                        if(attackLeft.hasEnded()) attackLeft.setIndex(4);
                        break;
                    case RIGHT:
                        attackRight.update();
                        if(attackRight.hasEnded()) attackRight.setIndex(4);
                        break;
                }

                // AFTER 25 FRAMES, GO BACK TO IDLE STATE AND RESET THE ANIMATIONS
                if(weaponTimer == 0) {

                    attackUp.reset();
                    attackDown.reset();
                    attackLeft.reset();
                    attackRight.reset();

                    weaponActive = false;
                    state = "IDLE";
                }
                break;

            case "GET_ITEM":
                velX = 0;
                velY = 0;
                // AFTER 2 SECONDS, TURN BACK TO IDLE STATE
                if(getAnimationTimer == 0) getAnimationTimer = 120;
                else getAnimationTimer--;

                if(getAnimationTimer == 0) state = "IDLE";
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

            case "KNOCKBACK":
                // GET A VECTOR IN THE OPPOSITE DIRECTION OF THE PLAYER
                int[] knockback = direction.getOpposite().getVector(3);
                velX = knockback[0];
                velY = knockback[1];

                x += velX;
                y += velY;
                // STORE HOW FAR THE PLAYER HAS MOVED
                knockbackDistance += (velX + velY);
                // IF THE PLAYER HAS MOVED A TILE, STOP THE KNOCKBACK
                if(Math.abs(knockbackDistance) >= room.getWidthOfTile()) {

                    knockbackDistance = 0;
                    state = "IDLE";
                }
                break;

            default:
                System.out.println(state);
                break;
        }

        // DECREASE INVINCIBILITY FRAMES, IF PLAYER HAS ANY
        if(invincibilityFrames > 0) invincibilityFrames--;
        if(health < 0) health = 0;

        // CREATE A RECTANGLE TO CHECK IF THINGS ARE GOING OFFSCREEN
        Rectangle screen = new Rectangle(room.getRoomWidth(), room.getRoomHeight());

        // CHECK COLLISIONS WITH PLAYER AND TILEMAP
        if(!state.equals("TRANSITION")) {
            // UPDATE THE COLLISION BOX FOR THE PLAYER
            setBounds(x + 8, y + 16, width - 16, height - 16);

            // IF THE PLAYER HITS A TILE, PLAYER GOES OUT OF KNOCKBACK
            if(handleCollisions() && state.equals("KNOCKBACK")) {

                knockbackDistance = 0;
                state = "IDLE";
            }

            // IF THE PLAYER GOES OFFSCREEN, PLAYER GOES OUT OF KNOCKBACK
            if(!screen.intersects(this.getBounds()) && state.equals("KNOCKBACK")) {

                knockbackDistance = 0;
                state = "IDLE";
            }

            handleEnemyCollisions();
            handleNonInteractables();       // HANDLES COLLISIONS THAT DO NOT NEED THE INTERACT KEY

            // INTERACT PREVIOUS PRESSED ENSURES THAT THE FUNCTION UPDATES ONLY ONCE EVEN WHEN HELD
            if(!interactPrevPressed && !interact) interactPrevPressed = true;
            if(interactPrevPressed && interact) {

                interactPrevPressed = false;
                handleInteractables();      // HANDLES COLLISIONS THAT REQUIRES INTERACTING
            }
        }
    }

    private void handleEnemyCollisions() {

        ArrayList<Enemy> enemies = room.getWorldEnemies();

        // HANDLES THE PLAYER TAKING DAMAGE
        if(invincibilityFrames == 0) {
            for(Enemy enemy : enemies) {
                // CHECKS FOR A DIRECT COLLISION WITH THE ENEMY
                if (checkCollisionWith(enemy) && enemy.getDamage() > 0) {

                    health -= enemy.getDamage();
                    state = "KNOCKBACK";
                    invincibilityFrames = 40;
                }
            }
        }

        // HANDLES THE PLAYER DEALING DAMAGE
        if(state.equals("ATTACK") && weaponActive) {

            for(Enemy enemy : enemies)
                if(this.getAttackRange().intersects(enemy.getBounds()))
                    enemy.checkDamageCollisions(playerWeapon);
        }
    }

    private void handleInteractables() {

        worldObjects = room.getWorldObjects();
        worldNPCS = room.getWorldNPCS();

        for (WorldObject object : worldObjects)
            if (object instanceof Interactable interactable && this.getItemRange().intersects(object.getBounds()))
                interactable.action(this);

        for(NPC npc : worldNPCS) {

            if(this.getItemRange().intersects(npc.getBounds())) {

                npc.action();
                if(npc.isInDialogue()) state = "DIALOGUE";
                if(!npc.isInDialogue()) state = "IDLE";
            }
        }
    }

    private void handleNonInteractables() {

        worldObjects = room.getWorldObjects();
        Iterator<WorldObject> iterator = worldObjects.iterator();

        while(iterator.hasNext())
        {
            // GET THE WORLD OBJECT
            WorldObject object = iterator.next();

            // CHECK IF IT IS A COLLECTIBLE
            if(object instanceof Collectible collectible) {

                if (checkCollisionWith(collectible.getBounds()))
                    if(collectible.action(this))
                        iterator.remove();
            }

            // CHECK IF IT IS A WEAPON
            if(object instanceof Weapon weapon) {

                if (checkCollisionWith(weapon.getBounds()))
                    if(weapon.playerAction(this))
                        iterator.remove();
            }
        }
    }

    // MAKE THE PLAYER GO INTO THE ITEM ANIMATION STATE
    public void enterItemState(WorldObject object) {

        this.state = "GET_ITEM";
        drawObject = object;

        if(object instanceof Weapon) {

            playerWeapon = (Weapon) object;
            weaponActive = false;
        }
    }

    // UPDATE THE PLAYER STATE VARIABLE
    private void updatePlayerState() {

            if(inputUp) state = "UP";
            if(inputDown) state = "DOWN";
            if(inputLeft) state = "LEFT";
            if(inputRight) state = "RIGHT";

            if(sprint && GameData.hasBoots) moveSpeed = 5;
            else moveSpeed = 3;

            if(inputAttack && GameData.swordLevel > 0) state = "ATTACK";

            if(!(inputUp || inputDown || inputLeft || inputRight || inputAttack)) state = "IDLE";
            if(transitionVelX != 0 || transitionVelY != 0) 	state = "TRANSITION";
    }

    // UPDATES PLAYER INPUT VARIABLES
    public void setInput(int key, boolean bool) {
        // MOVEMENT
        if(key == KeyEvent.VK_D) inputRight = bool;
        if(key == KeyEvent.VK_A) inputLeft = bool;
        if(key == KeyEvent.VK_W) inputUp = bool;
        if(key == KeyEvent.VK_S) inputDown = bool;
        if(key == KeyEvent.VK_SHIFT) sprint = bool;

        // ACTION KEYS
        if(key == KeyEvent.VK_J) inputAttack = bool;
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

    // RETURN ATTACK RANGE FOR HITTING ENEMIES, DEPENDING ON THE DIRECTION OF THE PLAYER
    private Rectangle getAttackRange() {

        return switch (direction) {

            case UP -> new Rectangle(x - 24, y - 12, 80, 32);
            case DOWN -> new Rectangle(x - 16, y + 44, 80, 32);
            case LEFT -> new Rectangle(x - 24, y - 8, 32, 80);
            case RIGHT -> new Rectangle(x + 40, y - 8, 32, 80);
        };
    }

    @Override
    public void draw(Graphics2D g2) {

        // IF THE PLAYER IS INVINCIBLE MAKE HIM FLICKER BY DRAWING HIM EVERY THIRD FRAME
        if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0)) {
            // SET BOTH DRAW X AND Y TO CURRENT X AND Y OF PLAYER
            drawX = x;
            drawY = y;

            switch(state) {

                case "IDLE", "TRANSITION", "DIALOGUE", "KNOCKBACK":

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

                case "ATTACK":

                    switch(direction) {

                        case UP: attackUp.draw(g2, drawX - width, drawY - height, width * 3, height * 3); break;
                        case DOWN: attackDown.draw(g2, drawX - width, drawY - height, width * 3, height * 3); break;
                        case LEFT: attackLeft.draw(g2, drawX - width, drawY - height, width * 3, height * 3); break;
                        case RIGHT: attackRight.draw(g2, drawX - width, drawY - height, width * 3, height * 3); break;
                    }
                    break;

                case "GET_ITEM":

                    g2.drawImage(Images.PlayerAssets.PLAYER_GET_ITEM, drawX, drawY, width, height, null);
                    drawObject.draw(drawX + drawObject.getWidth() / 4,
                            drawY - drawObject.getHeight() / 2, g2);

                    sparkle.draw(g2, drawX - GamePanel.TILE_SIZE + 8, drawY - GamePanel.TILE_SIZE - 24,
                            width * 2, height * 2);

                    sparkle.update();
                    break;

                default:

                    g2.setColor(Color.RED);
                    g2.drawRect(drawX, drawY, width, height);
                    break;
            }
        }

        drawDebug(g2);
        drawAttackDebug(g2);
    }

    public void setTransitionVector(int transitionVelX, int transitionVelY) {

        this.transitionVelX = transitionVelX;
        this.transitionVelY = transitionVelY;
    }

    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

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

    public void drawAttackDebug(Graphics2D g2) {

        g2.setColor(new Color(255, 0, 50, 60));
        switch(direction) {

            case UP: g2.fillRect(x - 24, y - 12, 80, 32); break;
            case DOWN: g2.fillRect(x  - 16, y + 44, 80, 32); break;
            case LEFT: g2.fillRect(x - 24, y - 8, 32, 80); break;
            case RIGHT: g2.fillRect(x + 40, y - 8, 32, 80); break;
        }
    }
}
