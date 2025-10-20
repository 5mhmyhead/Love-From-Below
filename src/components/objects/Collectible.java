package components.objects;

import components.entity.Player;
import utilities.SoundManager;
import utilities.SoundPlayer;

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
        // GET THE CURRENT SONG AND SAVE IT
        SoundPlayer current = SoundManager.getPlaying();
        SoundManager.stopAll();

        // PLAY THE SOUND EFFECT
        SoundManager.ITEM.play();

        Thread thread = new Thread(() -> {
            // WAIT FOR THE SOUND EFFECT TO FINISH
            while(!SoundManager.ITEM.isFinished());
            // STOP SOUND EFFECT
            SoundManager.ITEM.stop();
            // CONTINUE THE PREVIOUS SONG
            if(current != null) {
                if(current.isLooping()) current.loop();
                else current.play();
            }
        });
        thread.start();
    }
}
