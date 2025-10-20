package utilities;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.Objects;

// AN OBJECT THAT PLAYS A GIVEN SONG
public class SoundPlayer  {

    private final Object synchronizationLock = new Object();    // OBJECT AS A LOCK TO PREVENT SYNCHRONIZATION ISSUES
    private Clip clip;                                          // CLIP THAT HAS THE SONG
    private boolean isLooping;                                  // WHETHER THE CLIP SHOULD LOOP OR NOT

    // LOADS THE CLIP
    SoundPlayer(String path) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(SoundPlayer.class.getResource(path)));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // PLAYS THE CLIP WITHOUT LOOPING
    public void play() {

        try {

            if(clip != null) {
                // CREATE A THREAD TO PLAY THE CLIP
                new Thread(() -> {

                    synchronized(synchronizationLock) {

                        clip.stop();
                        clip.setFramePosition(0);
                        clip.start();
                    }
                }).start();
                // MAKE SURE THAT THE CLIP IS NOT LOOPING
                isLooping = false;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {

        if(clip == null) return;
        clip.stop();
    }

    public void loop()
    {
        try {

            if(clip != null) {
                // CREATE A THREAD TO LOOP THE CLIP
                new Thread(() -> {

                    synchronized(synchronizationLock) {

                        clip.stop();
                        clip.setFramePosition(0);
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                }).start();
                isLooping = true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setVolume(int relativeVolume) {

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(relativeVolume);
    }

    public boolean isPlaying()
    {
        return clip.isActive();
    }
    public boolean isFinished()
    {
        return clip.getMicrosecondPosition() == clip.getMicrosecondLength();
    }
    public boolean isLooping()
    {
        return isLooping;
    }
}
