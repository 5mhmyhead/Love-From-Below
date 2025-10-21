package components.entities;

import components.objects.Weapon;
import components.world.rooms.Room;

public abstract class Enemy extends Entity {

    private String priorState;

    private int stunTimer = 0;
    protected int damage;

    private Weapon weapon;

    public Enemy(int x, int y, Room room, int health, int damage, String state, int width, int height) {

        this.room = room;

        setCoordinates(x, y);
        setSize(width, height);

        this.width = width;
        this.height = height;

        this.health = health;
        this.damage = damage;
        this.state = state;
    }

    public void update() {

        if(state.equals("KNOCKBACK")) {
            // GET A VECTOR IN THE DIRECTION THE PLAYER IS FACING
            int[] knockback = room.getPlayer().getDirection().getVector(2);
            velX = knockback[0];
            velY = knockback[1];

            // STORE HOW MUCH THE ENEMY HAS MOVED
            knockbackDistance += (velX + velY);
            // STOP KNOCKBACK AFTER 3 TILES
            if(Math.abs(knockbackDistance) >= room.getWidthOfTile() * 2) {
                knockbackDistance = 0;
                state = priorState;
            }
        }

        updateHealth();
    }

    public void updateHealth() {

        checkDamageCollisions();

        if(invincibilityFrames > 0) invincibilityFrames--;
        if(health < 0) destroyFlag = true;

        //TODO ADD ITEM DROPS IF EVER
    }

    private void checkDamageCollisions() {

        if(weapon != null) {

            boolean collision = checkCollisionWith(weapon.getBounds());

            if(collision && invincibilityFrames == 0) {
                // THE ENEMY TAKES DAMAGE
                health -= weapon.getDamage();

                weapon.enemyAction(this);
                if(weapon.callsInvincibility()) invincibilityFrames = 30;
            }
        }
    }

    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    public int getDamage() { return damage; }

    public int getStunTimer() { return stunTimer; }
    public void setStunTimer(int stunTimer) { this.stunTimer = stunTimer; }
}
