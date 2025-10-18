package components.objects.nonInteractables;

import components.objects.WorldObject;
import components.world.rooms.Room;
import core.ui.GameData;
import utilities.Images;

import java.awt.*;

public class Sword extends WorldObject {

    public Sword(int x, int y, Room room) {

        this.x = x;
        this.y = y;

        this.hasCollision = false;

        this.image = Images.WorldObjects.SWORD;
        this.room = room;
    }

    @Override
    public void update() {

        GameData.swordLevel = 1;
        this.image = null;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, height, null);
    }
}
