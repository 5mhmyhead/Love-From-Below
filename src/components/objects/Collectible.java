package components.objects;

import components.entity.Player;

import java.awt.*;

//AN ITEM THAT PERFORMS AN ACTION WHEN COLLIDED WITH
public abstract class Collectible extends WorldObject {

    // THE ACTION TO BE TAKEN WHEN THE PLAYER COLLIDES WITH THE ITEM
    // RETURNS WHETHER THE ITEM SHOULD BE REMOVED
    public abstract boolean action(Player player);

    public void draw(int x, int y, Graphics2D g2) {
        g2.drawImage(image, x - width / 2, y - height / 2, width, height, null);
    }

    // PLAYS THE SOUND FOR COLLECTING THE ITEM
    public void playClip() {
        //TODO ADD SOUND SYSTEM
    }
}
