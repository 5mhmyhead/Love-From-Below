package components.entity.npcs;

import components.entity.Direction;
import components.entity.Entity;
import components.world.rooms.Room;
import core.GamePanel;
import core.ui.GameDialogue;
import utilities.Animation;
import utilities.Images;

import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Flerp extends Entity {

    private final Animation idleSleep, idleAwake, wakeUp, fallAsleep;
    private final GameDialogue dialogue;

    // CONSTRUCTOR
    public Flerp(int x, int y, String[] text, Room room) {

        setCoordinates(x, y);
        setSize(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
        setBounds(x, y, width, height);

        this.room = room;
        dialogue = new GameDialogue(this, text, 0, false);

        idleSleep = new Animation(50, true, Objects.requireNonNull(Images.npcAssets.FLERP_IDLE_SLEEP), width, height);
        idleAwake = new Animation(50, true, Objects.requireNonNull(Images.npcAssets.FLERP_IDLE), width, height);

        wakeUp = new Animation(80, false, Objects.requireNonNull(Images.npcAssets.FLERP_WAKE_UP), width, height);
        fallAsleep = new Animation(80, false, Objects.requireNonNull(Images.npcAssets.FLERP_FALL_ASLEEP), width, height);

        direction = Direction.DOWN;
        state = "IDLE_SLEEP";
    }

    @Override
    public void update() {

        switch (state) {
            case "IDLE_SLEEP":

                state = "WAKE_UP";

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {

                    @Override
                    public void run() {

                        wakeUp.update();

                        if(wakeUp.hasEnded()) {

                            state = "IDLE_AWAKE";
                            timer.cancel();
                        }
                    }
                };

                timer.schedule(task, 0, 10);
                break;

            case "IDLE_AWAKE":
                state = "DIALOGUE";
                break;

            case "DIALOGUE":

                if(!dialogue.hasEnded()) {
                    dialogue.update();

                    if(dialogue.hasEnded()) {

                        state = "IDLE_AWAKE";
                        dialogue.resetTo(5);
                    }
                }

                break;
        }
    }

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

            case "DIALOGUE":

                idleAwake.draw(g2, x, y, width, height);
                idleAwake.update();

                dialogue.draw(g2);
                break;

            default:

                g2.setColor(Color.RED);
                g2.drawRect(x, y, width, height);
                break;
        }
    }
}
