package components.objects;

import java.awt.*;

// A SOLID WORLD OBJECT THAT PERFORMS AN ACTION WHEN INTERACTED WITH
public abstract class Interactable extends WorldObject {

    public void draw(int x, int y, Graphics2D g2) {
        g2.drawImage(image, x - width / 2, y - height / 2, width, height, null);
    }

    // PLAYS THE SOUND FOR INTERACTING WITH THE ITEM
    public void playClip() {
        //TODO ADD SOUND SYSTEM
    }
}
