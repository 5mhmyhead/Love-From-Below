package core.ui;

import components.world.World;
import core.GamePanel;
import utilities.FontHandler;
import utilities.Images;

import java.awt.*;

public class GameMenu {

    private final World world;

    public GameMenu(World world) {

        this.world = world;
    }

    public void update() {}

    public void draw(Graphics2D g2) {
        drawWindow(g2, 300, 192, 100, 100);
    }

    public void drawWindow(Graphics2D g2, int x, int y, int width, int height) {

        int tileSize = GamePanel.TILE_SIZE;
        // SET THE COLOR FOR THE BACKGROUND OF THE DIALOGUE BOX AND FONT
        g2.setColor(new Color(7, 22, 33, 220));
        g2.setFont(FontHandler.maruMonica);

        // DRAW MENU BOX
        g2.fillRect(tileSize * 4, tileSize, tileSize * 8, tileSize * 10);
        g2.drawImage(Images.UI.MENU_BOX, 0, 0, null);
    }
}
