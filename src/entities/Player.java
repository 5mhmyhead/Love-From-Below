package entities;

import core.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    // PLAYER INPUTS
    private boolean inputLeft, inputUp, inputRight,
                    inputDown;

    // CONSTRUCTOR
    public Player() {
        setDefaultValues();
    }

    private void setDefaultValues() {

        x = 200;
        y = 200;
        moveSpeed = 3;

        state = "IDLE";
    }

    // UPDATE THE PLAYER STATE VARIABLE
    private void updatePlayerState() {

        if(inputUp) state = "UP";
        if(inputDown) state = "DOWN";
        if(inputLeft) state = "LEFT";
        if(inputRight) state = "RIGHT";

        if(!(inputUp || inputDown || inputLeft || inputRight))
            state = "IDLE";
    }

    // UPDATES PLAYER INPUT VARIABLES
    public void setInput(int key, boolean bool) {

        if(key == KeyEvent.VK_D) inputRight = bool;
        if(key == KeyEvent.VK_A) inputLeft = bool;
        if(key == KeyEvent.VK_W) inputUp = bool;
        if(key == KeyEvent.VK_S) inputDown = bool;
    }

    // UPDATES PLAYER POSITION, ANIMATION, ETC
    public void update() {

        switch(state) {
            case "IDLE":
                updatePlayerState();
                break;

            case "UP":
                y -= moveSpeed;
                updatePlayerState();
                break;

            case "DOWN":
                y += moveSpeed;
                updatePlayerState();
                break;

            case "LEFT":
                x -= moveSpeed;
                updatePlayerState();
                break;

            case "RIGHT":
                x += moveSpeed;
                updatePlayerState();
                break;
        }
    }

    public void draw(Graphics2D g2) {
        g2.fillRect(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }
}
