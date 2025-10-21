package components.entities.enemies;

import components.entities.Enemy;
import components.objects.misc.AnimationObject;
import components.world.rooms.Room;
import utilities.Animation;
import utilities.Images;

// A CLOUD ANIMATION WHEN AN ENEMY SPAWNS INTO A ROOM
public class Cloud extends AnimationObject {

    private final Enemy enemy;

    public Cloud(int x, int y, Room room, Enemy enemy) {

        super(x, y, new Animation(7, false,
                Images.Enemies.CLOUD_1, Images.Enemies.CLOUD_2, Images.Enemies.CLOUD_3), room);

        this.enemy = enemy;
    }

    public Enemy getEnemy()
    {
        return enemy;
    }
}
