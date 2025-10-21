package components.world.rooms;

import components.entities.Entity;
import components.entities.NPC;
import components.entities.Player;
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
    ArrayList<NPC> getWorldNPCS();

    Player getPlayer();
}
