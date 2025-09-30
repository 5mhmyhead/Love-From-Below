package entities;

import controller.KeyHandler;
import controller.PlayerController;
import core.GamePanel;

import java.awt.*;

public class Player extends Entity {

    GamePanel gamePanel;
    PlayerController controller;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        super();
        this.gamePanel = gamePanel;
        controller = new PlayerController(keyHandler);

        setDefaultValues();
    }

    public void setDefaultValues() {

        x = 200;
        y = 200;
        speed = 4;
    }

    public void update() {

        if(controller.isRequestingUp()) { y -= speed; }
        if(controller.isRequestingDown()) { y += speed; }
        if(controller.isRequestingLeft()) { x -= speed; }
        if(controller.isRequestingRight()) { x += speed; }
    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.black);
        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}
