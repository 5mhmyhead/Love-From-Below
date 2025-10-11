package components.entity.npcs;

import components.entity.Direction;
import components.entity.Entity;
import components.rooms.Room;
import components.ui.GameDialogue;
import utilities.Animation;
import utilities.Images;

import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Flerp extends Entity {

    private final Animation idleSleep, idleAwake, wakeUp, fallAsleep;

    private final String[] text;
    private GameDialogue dialogue;

    // CONSTRUCTOR
    public Flerp(int x, int y, String[] text, Room room) {

        this.x = x;
        this.y = y;

        this.room = room;
        this.text = text;

        hitbox = new Rectangle(x, y, width, height);
        dialogue = new GameDialogue(text, 0, true);

        idleSleep = new Animation(50, true, Objects.requireNonNull(Images.npcAssets.FLERP_IDLE_SLEEP), width, height);
        idleAwake = new Animation(50, true, Objects.requireNonNull(Images.npcAssets.FLERP_IDLE), width, height);

        wakeUp = new Animation(80, false, Objects.requireNonNull(Images.npcAssets.FLERP_WAKE_UP), width, height);
        fallAsleep = new Animation(80, false, Objects.requireNonNull(Images.npcAssets.FLERP_FALL_ASLEEP), width, height);

        direction = Direction.DOWN;
        state = "IDLE_SLEEP";
    }

    @Override
    public void update() {

        Timer timer = new Timer();

        if(state.equals("IDLE_SLEEP")) {
            state = "WAKE_UP";

            TimerTask task = new TimerTask() {

                @Override
                public void run() {

                    wakeUp.update();
                    if(wakeUp.hasEnded()) { state = "READY_FOR_DIALOGUE"; }
                }
            };

            timer.schedule(task, 0, 100);
        }

        if(state.equals("READY_FOR_DIALOGUE")) {
            dialogue.update();
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

            case "READY_FOR_DIALOGUE":
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
