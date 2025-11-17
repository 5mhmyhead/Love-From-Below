package components.objects.interactables;

import components.entities.Player;
import components.objects.Interactable;
import components.world.rooms.Room;
import utilities.Images;

import java.awt.*;

public class NormalChest extends Interactable {

    private String state;

    public NormalChest(int x, int y, Room room) {

        this.x = x;
        this.y = y;

        this.state = "CLOSED";
        this.hasCollision = true;
        this.hasTextDialogue = false;

        this.image = Images.WorldObjects.NORMAL_CHEST_CLOSED;
        this.room = room;
    }

    @Override
    public void update() {}

    @Override
    public boolean action(Player player) {

        if(state.equals("CLOSED")) {
            playClip();
            System.out.println("wow you got an item not");
            state = "OPENED";
        }

        return false;
    }

    @Override
    public void draw(Graphics2D g2) {

        if(state.equals("CLOSED")) {
            g2.drawImage(image, x, y, width, height, null);
        } else {

            image = Images.WorldObjects.NORMAL_CHEST_OPENED;
            g2.drawImage(image, x, y, width, height, null);
        }
    }
}
