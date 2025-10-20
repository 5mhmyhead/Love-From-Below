package components.objects.collectibles;

import components.entity.Player;
import components.objects.Collectible;
import components.world.rooms.Room;
import core.ui.GameData;
import utilities.Images;

import java.awt.*;

public class Sword extends Collectible {

    public Sword(int x, int y, Room room) {

        this.x = x;
        this.y = y;

        this.hasCollision = false;

        this.image = Images.WorldObjects.SWORD;
        this.room = room;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean action(Player player) {

        GameData.swordLevel = 1;
        player.enterItemState(this);
        playClip();

        return true;
    }

    @Override
    public void draw(Graphics2D g2) {

        if(GameData.swordLevel == 0)
            g2.drawImage(image, x, y, width, height, null);
    }
}
