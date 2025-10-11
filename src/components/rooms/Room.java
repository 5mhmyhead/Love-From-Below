package components.rooms;

import components.entity.Entity;
import components.entity.Player;
import components.objects.WorldObject;
import utilities.Tile;

import java.util.ArrayList;

public interface Room {

    // RETURN WIDTH OR HEIGHT OF ROOM
    int getRoomWidth();
    int getRoomHeight();

    // RETURN COLUMNS OR ROWS OF ROOM
    int getNumOfColumns();
    int getNumOfRows();

    // RETURN WIDTH OR HEIGHT OF TILE
    int getWidthOfTile();
    int getHeightOfTile();

    // RETURNS THE ROW/COLUMN OF THE ROOM
    int getId();

    // RETURNS A TILE IN THE ROOM
    Tile getTile(int column, int row);

    // GETS ENTITIES/ENEMIES/OBJECTS IN THE ROOM
    ArrayList<WorldObject> getWorldObjects();
    ArrayList<Entity> getWorldNPCS();

    // RETURNS THE PLAYER IN THE ROOM
    Player getPlayer();
}
