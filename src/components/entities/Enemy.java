package components.entities;

import components.objects.Weapon;
import components.world.rooms.Room;

import java.awt.*;

public abstract class Enemy extends Entity {

    protected String priorState;
    protected int stunTimer = 0;

    protected boolean knockbackResist;
    protected int damage;

    public Enemy(int x, int y, Room room, int health, int damage, boolean knockbackResist, String state, int width, int height) {

        this.room = room;

        setCoordinates(x, y);
        setSize(width, height);

        this.width = width;
        this.height = height;

        this.health = health;
        this.damage = damage;
        this.state = state;
        this.knockbackResist = knockbackResist;
    }

    @Override
    public void update() {

        if(state.equals("KNOCKBACK")) {
            // GET THE OPPOSITE DIRECTION OF THE PLAYER
            this.direction = room.getPlayer().direction.getOpposite();

            // GET A VECTOR IN THE DIRECTION THE PLAYER IS FACING
            int[] knockback = room.getPlayer().getDirection().getVector(2);
            velX = knockback[0];
            velY = knockback[1];

            x += velX;
            y += velY;
            // STORE HOW MUCH THE ENEMY HAS MOVED
            knockbackDistance += (velX + velY);
            // STOP KNOCKBACK AFTER 1 TILE
            if(Math.abs(knockbackDistance) >= room.getWidthOfTile()) {

                knockbackDistance = 0;
                state = priorState;
            }
        }

        updateHealth();
    }

    public void updateHealth() {

        if(invincibilityFrames > 0) invincibilityFrames--;
        if(health <= 0) destroyFlag = true;

        //TODO ADD ITEM DROPS IF EVER
    }

    public void checkDamageCollisions(Weapon weapon) {

        if(weapon != null && invincibilityFrames == 0) {
            // THE ENEMY TAKES DAMAGE
            health -= weapon.getDamage();

            // IF THE ENEMY IS NOT RESISTANT TO KNOCKBACK, STATE BECOMES KNOCKBACK
            if(!(knockbackResist)) {

                priorState = state;
                state = "KNOCKBACK";
            }

            weapon.enemyAction(this);
            if(weapon.callsInvincibility()) invincibilityFrames = 30;
        }
    }

    public int getDamage() { return damage; }

    public int getStunTimer() { return stunTimer; }
    public void setStunTimer(int stunTimer) { this.stunTimer = stunTimer; }
}
