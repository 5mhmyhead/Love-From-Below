package components.objects.collectibles;

import components.entities.Player;
import components.objects.Collectible;
import components.world.rooms.Room;
import core.ui.GameData;
import utilities.Images;

import java.awt.*;

public class Candle extends Collectible  {

    public Candle(int x, int y, Room room) {

        this.x = x;
        this.y = y;

        this.hasCollision = false;

        this.image = Images.WorldObjects.CANDLE;
        this.room = room;
    }

    @Override
    public void update() {}

    @Override
    public boolean action(Player player) {

        if(!GameData.hasCandle) {

            GameData.hasCandle = true;
            player.enterItemState(this);
            playClip();
        }

        return true;
    }

    @Override
    public void draw(Graphics2D g2) {

        if(!GameData.hasCandle)
            g2.drawImage(image, x, y, width, height, null);
    }
}
