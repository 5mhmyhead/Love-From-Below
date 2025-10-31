package components.entities.npcs;

import components.entities.Direction;
import components.entities.NPC;
import components.world.rooms.Room;
import core.GamePanel;
import core.ui.GameDialogue;
import utilities.Animation;
import utilities.Images;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Flerp extends NPC {

    private final Animation idleSleep, idleAwake, wakeUp, fallAsleep;
    private final GameDialogue dialogueFirstTime;
    private final GameDialogue dialogueDefault;

    // CONSTRUCTOR
    public Flerp(int x, int y, ArrayList<String[]> dialogue, Room room) {

        setCoordinates(x, y);
        setSize(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        setBounds(x, y, width, height);

        this.room = room;
        dialogueFirstTime = new GameDialogue(this, Images.npcAssets.FLERP_PORTRAIT, dialogue.get(0), 0, false);
        dialogueDefault = new GameDialogue(this, Images.npcAssets.FLERP_PORTRAIT, dialogue.get(1), 0, false);

        idleSleep = new Animation(50, true, Objects.requireNonNull(Images.npcAssets.FLERP_IDLE_SLEEP), width, height);
        idleAwake = new Animation(50, true, Objects.requireNonNull(Images.npcAssets.FLERP_IDLE), width, height);

        wakeUp = new Animation(80, false, Objects.requireNonNull(Images.npcAssets.FLERP_WAKE_UP), width, height);
        fallAsleep = new Animation(80, false, Objects.requireNonNull(Images.npcAssets.FLERP_FALL_ASLEEP), width, height);

        direction = Direction.DOWN;
        state = "IDLE_SLEEP";
    }

    // FIXME timer.schedule(toSleep, 5000, 20);
    @Override
    public void action() {

        Timer timer = new Timer();

        TimerTask toSleep = new TimerTask() {

            @Override
            public void run() {

                state = "FALL_ASLEEP";
                fallAsleep.update();

                if(fallAsleep.hasEnded()) {

                    state = "IDLE_SLEEP";
                    timer.cancel();
                    fallAsleep.reset();
                }
            }
        };

        TimerTask wakesUp = new TimerTask() {

            @Override
            public void run() {

                wakeUp.update();

                if(wakeUp.hasEnded()) {

                    state = "IDLE_AWAKE";
                    timer.cancel();
                    wakeUp.reset();
                }
            }
        };

        switch (state) {
            case "IDLE_SLEEP":
                state = "WAKE_UP";
                timer.schedule(wakesUp, 0, 20);
                break;

            case "IDLE_AWAKE":

                if(!dialogueFirstTime.hasEnded()) state = "DIALOGUE_FIRST_TIME";
                else state = "DIALOGUE_DEFAULT";
                inDialogue = true;
                break;

            case "DIALOGUE_FIRST_TIME":

                if(!dialogueFirstTime.hasEnded()) {

                    dialogueFirstTime.update();
                    if(dialogueFirstTime.hasEnded()) {

                        state = "IDLE_AWAKE";
                        inDialogue = false;
                    }
                }
                break;

            case "DIALOGUE_DEFAULT":

                if(!dialogueDefault.hasEnded()) {

                    dialogueDefault.update();
                    if(dialogueDefault.hasEnded()) {

                        state = "IDLE_AWAKE";
                        inDialogue = false;
                        dialogueDefault.reset();
                    }
                }
                break;
        }
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics2D g2) {

        switch (state) {

            case "IDLE_SLEEP":

                idleSleep.draw(g2, x, y, width, height);
                idleSleep.update();
                break;

            case "IDLE_AWAKE":

                idleAwake.draw(g2, x, y, width, height);
                idleAwake.update();
                break;

            case "FALL_ASLEEP":

                fallAsleep.draw(g2, x, y, width, height);
                fallAsleep.update();
                break;

            case "WAKE_UP":

                wakeUp.draw(g2, x, y, width, height);
                wakeUp.update();
                break;

            case "DIALOGUE_FIRST_TIME", "DIALOGUE_DEFAULT":

                idleAwake.draw(g2, x, y, width, height);
                idleAwake.update();

                if(!dialogueFirstTime.hasEnded()) dialogueFirstTime.draw(g2);
                if(dialogueFirstTime.hasEnded()) dialogueDefault.draw(g2);
                break;

            default:

                g2.setColor(Color.RED);
                g2.drawRect(x, y, width, height);
                break;
        }
    }


}
