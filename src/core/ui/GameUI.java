package core.ui;

import components.entity.Player;
import components.world.World;
import utilities.Images;

import java.awt.*;

public class GameUI {

    private final World world;
    private final Player player;

    private final int width;
    private final int height;

    public GameUI(World world) {

        width = 768;
        height = 576;

        this.world = world;
        this.player = world.getPlayer();
    }

    public void draw(Graphics2D g2) {

        drawHealthBar(g2);
    }

    private void drawHealthBar(Graphics2D g2) {

        int xOrigin = 20;
        int yOrigin = 20;

        g2.drawImage(Images.UI.HEALTH_BAR_EMPTY, xOrigin, yOrigin, null);

        for(int i = 1; i <= world.getPlayer().getHealth(); i++) {

            if(i % 2 == 0) {
                yOrigin = 38;
            } else {
                yOrigin = 20;
            }

            g2.drawImage(Images.UI.HEART, xOrigin, yOrigin, null);
            xOrigin += 21;
        }
    }
}
