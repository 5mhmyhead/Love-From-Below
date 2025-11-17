package components.objects.interactables;

import components.entities.Player;
import components.objects.Interactable;
import components.world.rooms.Room;
import utilities.Images;

import java.awt.*;

public class CandleBush extends Interactable {

    public CandleBush(int x, int y, Room room) {

        this.x = x;
        this.y = y;

        this.hasCollision = true;
        this.hasTextDialogue = true;
        this.inTextDialogue = false;

        this.image = Images.WorldObjects.CANDLE_BUSH;
        this.room = room;
    }

    @Override
    public void update() {}

    @Override
    public boolean action(Player player) {

        // TODO ADD LOGIC HERE: DIALOGUE IF NO SWORD
        return false;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, height, null);
    }
}
