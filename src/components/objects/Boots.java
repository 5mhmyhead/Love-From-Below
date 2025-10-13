package components.objects;

import components.world.rooms.Room;
import utilities.GameData;
import utilities.Images;

import java.awt.*;

public class Boots extends WorldObject {

    public Boots(int x, int y, Room room) {

        this.x = x;
        this.y = y;

        this.hasCollision = false;

        this.image = Images.WorldObjects.BOOTS;
        this.room = room;
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
