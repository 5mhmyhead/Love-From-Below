package components.ui;

import components.world.World;
import utilities.FontHandler;

import java.awt.*;

public class GameMenu {

    private final World world;
    private final int width;
    private final int height;

    private int selectedIndex;

    public GameMenu(World world) {
        // MENU DIMENSIONS
        width = 768;
        height = 576;
        selectedIndex = 0;

        this.world = world;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {

        drawWindow(g2, 300, 192, 100, 100);
    }

    public void drawWindow(Graphics2D g2, int x, int y, int width, int height) {

        g2.setColor(Color.black);
        g2.setFont(FontHandler.comicoro);
        g2.drawString("PAUSED!!!", x, y);
    }
}
