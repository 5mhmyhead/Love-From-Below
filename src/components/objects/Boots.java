package components.objects;

import components.rooms.Room;
import core.GamePanel;
import utilities.GameData;
import utilities.Images;

import java.awt.*;

public class Boots extends WorldObject {

    public Boots(int x, int y, Room room) {

        this.x = x;
        this.y = y;

        this.width = GamePanel.TILE_SIZE;
        this.height = GamePanel.TILE_SIZE;

        this.room = room;
        this.hasCollision = false;

        this.image = Images.WorldObjects.BOOTS;
    }

    @Override
    public void update() {

        GameData.hasBoots = true;
        this.image = null;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, height, null);
    }
}
