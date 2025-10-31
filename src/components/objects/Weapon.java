package components.objects;

import components.entities.Enemy;
import components.entities.Player;
import utilities.SoundManager;
import utilities.SoundPlayer;

import java.awt.*;

public abstract class Weapon extends WorldObject {

    protected int damage;

    public int startUp;      // THE TIME WHEN THE WEAPON SHOULD DEAL DAMAGE IN THE ANIMATION
    public int windDown;     // THE TIME WHEN THE WEAPON SHOULD STOP DEALING DAMAGE IN THE ANIMATION

    // THE ACTION TO BE TAKEN WHEN THE PLAYER COLLIDES WITH THE ITEM
    // RETURNS WHETHER THE ITEM SHOULD BE REMOVED
    public abstract boolean playerAction(Player player);
    public abstract void enemyAction(Enemy enemy);

    // TODO HAVE DIFFERENT SOUND EFFECTS FOR DIFFERENT TYPES OF WORLD OBJECTS
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

    // WHETHER THE ENEMY SHOULD EXPERIENCE INVINCIBILITY FRAMES AFTER BEING HIT
    public abstract boolean callsInvincibility();
    public int getDamage() { return damage; }
}
