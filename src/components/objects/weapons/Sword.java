package components.objects.weapons;

import components.entities.Enemy;
import components.entities.Player;
import components.objects.Weapon;
import components.world.rooms.Room;
import core.ui.GameData;
import utilities.Images;

import java.awt.*;

public class Sword extends Weapon {

    public Sword(int x, int y, Room room) {

        this.damage = 1;

        this.startUp = 20;
        this.windDown = 12;

        this.x = x;
        this.y = y;

        this.hasCollision = false;

        this.image = Images.WorldObjects.SWORD;
        this.room = room;
    }

    @Override
    public void update() {}

    @Override
    public boolean playerAction(Player player) {

        if(GameData.swordLevel == 0) {

            GameData.swordLevel = 1;
            player.enterItemState(this);
            //playClip();
        }

        return true;
    }

    @Override
    public void enemyAction(Enemy enemy) {

    }

    @Override
    public void draw(Graphics2D g2) {

        if(GameData.swordLevel == 0)
            g2.drawImage(image, x, y, width, height, null);
    }

    @Override
    public boolean callsInvincibility() { return true; }
}
