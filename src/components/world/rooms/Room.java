package components.world.rooms;

import components.entity.Entity;
import components.entity.Player;
import components.objects.WorldObject;
import utilities.Tile;

import java.util.ArrayList;

public interface Room {

    int getRoomWidth();
    int getRoomHeight();

    int getNumOfColumns();
    int getNumOfRows();

    int getWidthOfTile();
    int getHeightOfTile();

    // RETURNS THE ROW/COLUMN OF THE ROOM
    int getId();
    Tile getTile(int column, int row);

    ArrayList<WorldObject> getWorldObjects();
    ArrayList<Entity> getWorldNPCS();

    Player getPlayer();
}
