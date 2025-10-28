package core.ui;

import components.world.World;
import utilities.Images;

import java.awt.*;

public class GameUI {

    private final World world;

    public GameUI(World world) {

        this.world = world;
    }

    public void draw(Graphics2D g2) {

        drawHealthBar(g2);
    }

    private void drawHealthBar(Graphics2D g2) {

        int xOrigin = 20;
        int yOrigin = 20;

        g2.drawImage(Images.UI.HEALTH_BAR_PLAYER, xOrigin, yOrigin, null);

        // DRAW THE MAX AMOUNT OF HEARTS OF THE PLAYER
        for(int i = 1; i <= world.getPlayer().getMaxHealth(); i++) {

            if(i % 2 == 0) yOrigin = 38;
            else yOrigin = 20;

            g2.drawImage(Images.UI.HEART_EMPTY, xOrigin, yOrigin, null);
            xOrigin += 21;
        }

        xOrigin = 20;
        // DRAW THE AMOUNT OF HEARTS THAT THE PLAYER CURRENTLY HAS
        for(int i = 1; i <= world.getPlayer().getHealth(); i++) {

            if(i % 2 == 0) yOrigin = 38;
            else yOrigin = 20;

            g2.drawImage(Images.UI.HEART, xOrigin, yOrigin, null);
            xOrigin += 21;
        }
    }
}
