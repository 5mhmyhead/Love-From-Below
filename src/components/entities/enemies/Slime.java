package components.entities.enemies;

import components.entities.Direction;
import components.entities.Enemy;
import components.world.rooms.Room;
import core.GamePanel;
import utilities.Animation;
import utilities.Images;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.Objects;

public class Slime extends Enemy {

    private int movementRefreshTimer;

    private Animation walkUp, walkDown, walkLeft, walkRight,
            attackUp, attackDown, attackLeft, attackRight,
            damagedUp, damagedDown, damagedLeft, damagedRight;

    public Slime(int x, int y, Direction direction, Room room) {

        super(x, y, room, 1, 1, "MOVING", GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

        this.direction = direction;
        setBounds(x + width / 4, y + height / 3, width / 2, height / 2);

        velX = 0;
        velY = 0;
        moveSpeed = 1;

        walkUp = new Animation(40, true, Images.Enemies.Slime.SLIME_UP_1, Images.Enemies.Slime.SLIME_UP_2);
        walkDown = new Animation(40, true, Images.Enemies.Slime.SLIME_DOWN_1, Images.Enemies.Slime.SLIME_DOWN_2);
        walkLeft = new Animation(40, true, Images.Enemies.Slime.SLIME_LEFT_1, Images.Enemies.Slime.SLIME_LEFT_2);
        walkRight = new Animation(40, true, Images.Enemies.Slime.SLIME_RIGHT_1, Images.Enemies.Slime.SLIME_RIGHT_2);

        attackUp = new Animation(20, true, Objects.requireNonNull(Images.Enemies.Slime.SLIME_UP_ATTACK),
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        attackDown = new Animation(20, true, Objects.requireNonNull(Images.Enemies.Slime.SLIME_DOWN_ATTACK),
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        attackLeft = new Animation(20, true, Objects.requireNonNull(Images.Enemies.Slime.SLIME_LEFT_ATTACK),
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        attackRight = new Animation(20, true, Objects.requireNonNull(Images.Enemies.Slime.SLIME_RIGHT_ATTACK),
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

        damagedUp = new Animation(20, true, Objects.requireNonNull(Images.Enemies.Slime.SLIME_UP_DAMAGED),
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        damagedDown = new Animation(20, true, Objects.requireNonNull(Images.Enemies.Slime.SLIME_DOWN_DAMAGED),
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        damagedLeft = new Animation(20, true, Objects.requireNonNull(Images.Enemies.Slime.SLIME_LEFT_DAMAGED),
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        damagedRight = new Animation(20, true, Objects.requireNonNull(Images.Enemies.Slime.SLIME_RIGHT_DAMAGED),
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }

    public void update() {

        setBounds(x + width / 4, y + height / 3, width / 2, height / 2);

        switch(state) {

            case "MOVING":

                int[] vector = direction.getVector(moveSpeed);
                velX = vector[0];
                velY = vector[1];

                switch(direction) {

                    case UP:
                        walkUp.update();
                        y += velY;
                        break;

                    case DOWN:
                        walkDown.update();
                        y += velY;
                        break;

                    case LEFT:
                        walkLeft.update();
                        x += velX;
                        break;

                    case RIGHT:
                        walkRight.update();
                        x += velX;
                        break;
                }

                if(x < 0) direction = Direction.RIGHT;
                if(y < 0) direction = Direction.DOWN;

                if(x > room.getRoomWidth()) direction = Direction.LEFT;
                if(y > room.getRoomHeight()) direction = Direction.UP;
                break;

            default:
                System.out.println(state);
                break;
        }

        System.out.println(handleCollisions());

        if(handleCollisions() && movementRefreshTimer == 0) {

            movementRefreshTimer = 40;
            direction = Direction.getExcludedRandom(direction);
        }

        if(movementRefreshTimer > 0) movementRefreshTimer--;
        super.update();
    }

    @Override
    public void draw(Graphics2D g2) {

        drawDebug(g2);

        drawX = x;
        drawY = y;

        switch(state) {

            case "MOVING":

                switch(direction) {

                    case UP: walkUp.draw(g2, drawX, drawY, width, height); break;
                    case DOWN: walkDown.draw(g2, drawX, drawY, width, height); break;
                    case LEFT: walkLeft.draw(g2, drawX, drawY, width, height); break;
                    case RIGHT: walkRight.draw(g2, drawX, drawY, width, height); break;
                }
                break;
        }
    }
}
