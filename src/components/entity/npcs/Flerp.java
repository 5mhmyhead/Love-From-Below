package components.entity.npcs;

import components.entity.Direction;
import components.entity.Entity;
import components.rooms.Room;
import components.world.World;
import core.GamePanel;
import utilities.Animation;
import utilities.Images;

import java.awt.*;
import java.util.Objects;

public class Flerp extends Entity {

    private Animation idleSleep, idleAwake, wakeUp, fallAsleep;

    // CONSTRUCTOR
    public Flerp(int x, int y, Room room) {

        this.x = x;
        this.y = y;

        this.width = GamePanel.TILE_SIZE;
        this.height = GamePanel.TILE_SIZE;

        this.room = room;

        idleSleep = new Animation(50, true, Objects.requireNonNull(Images.npcAssets.FLERP_IDLE_SLEEP), width, height);
        idleAwake = new Animation(50, true, Objects.requireNonNull(Images.npcAssets.FLERP_IDLE), width, height);

        wakeUp = new Animation(100, false, Objects.requireNonNull(Images.npcAssets.FLERP_WAKE_UP), width, height);
        fallAsleep = new Animation(100, false, Objects.requireNonNull(Images.npcAssets.FLERP_FALL_ASLEEP), width, height);

        direction = Direction.DOWN;
        state = "IDLE_SLEEP";
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

        switch (state) {

            case "IDLE_SLEEP":
                idleSleep.update();
                idleSleep.draw(g2, x, y, width, height);
                break;

            case "IDLE_AWAKE":
                idleAwake.update();
                idleAwake.draw(g2, x, y, width, height);
                break;

            case "FALL_ASLEEP":
                fallAsleep.update();
                fallAsleep.draw(g2, x, y, width, height);
                break;

            case "WAKE_UP":
                wakeUp.update();
                wakeUp.draw(g2, x, y, width, height);
                break;

            default:

                g2.setColor(Color.RED);
                g2.drawRect(x, y, width, height);
                break;
        }
    }
}
