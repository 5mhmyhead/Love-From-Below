package utilities;

public class SoundManager {

    // ALL AUDIO FILES
    public static final SoundPlayer INTRO = new SoundPlayer("/audio/Intro.wav");
    public static final SoundPlayer OVERWORLD = new SoundPlayer("/audio/Overworld.wav");
    public static final SoundPlayer ITEM = new SoundPlayer("/audio/Item.wav");

    // ARRAY STORING ALL AUDIO FILES
    private static final SoundPlayer[] soundPlayers = new SoundPlayer[] {INTRO, OVERWORLD, ITEM};

    // STOPS EVERY SOUND PLAYER
    public static void stopAll() {

        for(SoundPlayer soundPlayer : soundPlayers)
            soundPlayer.stop();
    }

    // RETURNS THE FIRST SOUND PLAYER IT FINDS THAT IS PLAYING
    public static SoundPlayer getPlaying() {

        for(SoundPlayer soundPlayer : soundPlayers)
            if(soundPlayer.isPlaying()) return soundPlayer;

        return null;
    }
}
