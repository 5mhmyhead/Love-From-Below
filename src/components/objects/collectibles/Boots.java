package components.objects.collectibles;

import components.entity.Player;
import components.objects.Collectible;
import components.world.rooms.Room;
import core.ui.GameData;
import utilities.Images;

import java.awt.*;

public class Boots extends Collectible {

    public Boots(int x, int y, Room room) {

        this.x = x;
        this.y = y;

        this.hasCollision = false;

        this.image = Images.WorldObjects.BOOTS;
        this.room = room;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean action(Player player) {

        GameData.hasBoots = true;
        player.enterItemState(this);
        playClip();

        return true;
    }

    @Override
    public void draw(Graphics2D g2) {

        if(!GameData.hasBoots)
            g2.drawImage(image, x, y, width, height, null);
    }
}
