package components.rooms;

import components.entity.Player;
import utilities.Tile;

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

    // RETURNS THE PLAYER IN THE ROOM
    Player getPlayer();
}
