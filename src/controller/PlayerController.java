package controller;

import java.awt.event.KeyEvent;

public class PlayerController {

    private final KeyHandler keyHandler;

    public PlayerController(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public boolean isRequestingUp() {
        return keyHandler.isPressed(KeyEvent.VK_W);
    }

    public boolean isRequestingDown() {
        return keyHandler.isPressed(KeyEvent.VK_S);
    }

    public boolean isRequestingLeft() {
        return keyHandler.isPressed(KeyEvent.VK_A);
    }

    public boolean isRequestingRight() {
        return keyHandler.isPressed(KeyEvent.VK_D);
    }
}
