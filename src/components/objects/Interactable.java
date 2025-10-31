package components.objects;

import components.entities.Player;
import utilities.SoundManager;
import utilities.SoundPlayer;

import java.awt.*;

// A SOLID WORLD OBJECT THAT PERFORMS AN ACTION WHEN INTERACTED WITH
public abstract class Interactable extends WorldObject {

    // THE ACTION TO BE TAKEN WHEN THE PLAYER COLLIDES WITH THE ITEM
    // RETURNS WHETHER THE ITEM SHOULD BE REMOVED
    public abstract boolean action(Player player);

    // PLAYS THE SOUND FOR INTERACTING WITH THE ITEM
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
