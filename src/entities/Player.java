package entities;

import core.GamePanel;
import controller.KeyHandler;
import controller.PlayerController;
import utilities.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    PlayerController controller;

    // TEMPORARY VARIABLES
    int spriteCounter = 0;
    int spriteNumber = 1;

    public Player(KeyHandler keyHandler) {

        controller = new PlayerController(keyHandler);
        setDefaultValues();
        getPlayerSpritesheet();
    }

    public void setDefaultValues() {

        x = 200;
        y = 200;
        speed = 4;
        direction = "down";
    }

    public void getPlayerSpritesheet() {

        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        spritesheet = new Spritesheet(sprite);
    }

    public void update() {

        int frameInterval = 12;

        if(controller.isRequestingUp()
                || controller.isRequestingDown()
                || controller.isRequestingLeft()
                || controller.isRequestingRight()) {

            if(controller.isRequestingUp()) { direction = "up"; y -= speed; }
            if(controller.isRequestingDown()) { direction = "down"; y += speed; }
            if(controller.isRequestingLeft()) { direction = "left"; x -= speed; }
            if(controller.isRequestingRight()) { direction = "right"; x += speed; }

            spriteCounter++;
            if(spriteCounter > frameInterval) {

                if(spriteNumber < 4) {
                    spriteNumber++;
                } else {
                    spriteNumber = 1;
                }

                spriteCounter = 0;
            }
        } else {
            direction = "down";
            spriteNumber = 1;
        }
    }

    public void draw(Graphics2D g2) {

        sprite = null;

        switch(direction) {
            case "down":
                switch(spriteNumber) {
                    case 1: sprite = spritesheet.grabImage(1, 13, 48, 48); break;
                    case 2: sprite = spritesheet.grabImage(1, 14, 48, 48); break;
                    case 3: sprite = spritesheet.grabImage(1, 15, 48, 48); break;
                    case 4: sprite = spritesheet.grabImage(1, 16, 48, 48); break;
                }
                break;

            case "up":
                switch(spriteNumber) {
                    case 1: sprite = spritesheet.grabImage(1, 9, 48, 48); break;
                    case 2: sprite = spritesheet.grabImage(1, 10, 48, 48); break;
                    case 3: sprite = spritesheet.grabImage(1, 11, 48, 48); break;
                    case 4: sprite = spritesheet.grabImage(1, 12, 48, 48); break;
                }
                break;

            case "left":
                switch(spriteNumber) {
                    case 1: sprite = spritesheet.grabImage(1, 5, 48, 48); break;
                    case 2: sprite = spritesheet.grabImage(1, 6, 48, 48); break;
                    case 3: sprite = spritesheet.grabImage(1, 7, 48, 48); break;
                    case 4: sprite = spritesheet.grabImage(1, 8, 48, 48); break;
                }
                break;

            case "right":
                switch(spriteNumber) {
                    case 1: sprite = spritesheet.grabImage(1, 1, 48, 48); break;
                    case 2: sprite = spritesheet.grabImage(1, 2, 48, 48); break;
                    case 3: sprite = spritesheet.grabImage(1, 3, 48, 48); break;
                    case 4: sprite = spritesheet.grabImage(1, 4, 48, 48); break;
                }
                break;
        }

        g2.drawImage(sprite, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
    }
}
