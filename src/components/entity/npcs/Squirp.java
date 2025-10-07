package components.entity.npcs;

import components.entity.Direction;
import components.entity.Entity;
import components.world.World;
import core.GamePanel;

import java.awt.*;

public class Squirp extends Entity {

    private final World world;

    // CONSTRUCTOR
    public Squirp(World world) {

        this.world = world;
        this.room = world.getCurrentRoom();

        setDefaultValues();
    }

    private void setDefaultValues() {

        setCoordinates(200, 280);

        drawX = x;
        drawY = y;

        moveSpeed = 6;

        width = GamePanel.TILE_SIZE;
        height = GamePanel.TILE_SIZE;

        direction = Direction.DOWN;
        state = "IDLE";
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2d) {

    }
}
