package components.objects.misc;

import components.objects.WorldObject;
import components.world.rooms.Room;
import utilities.Animation;

import java.awt.*;

// AN OBJECT THAT IS PLACED IN THE WORLD AND PLAYS AN ANIMATION
public class AnimationObject extends WorldObject {

    private final Animation animation;

    public AnimationObject(int x, int y, Animation animation, Room room)
    {
        this.x = x;
        this.y = y;

        this.width = animation.getWidth();
        this.height = animation.getHeight();

        this.animation = animation;
        this.room = room;
    }

    @Override
    public void update() { animation.update(); }

    @Override
    public void draw(Graphics2D g2) { animation.draw(g2, x, y, width, height); }

    public Animation getAnimation()
    {
        return animation;
    }

    public int getWidth()
    {
        return getAnimation().getWidth();
    }
    public int getHeight()
    {
        return getAnimation().getHeight();
    }
}
