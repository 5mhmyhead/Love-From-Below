package components.world;

import components.entity.Direction;
import components.entity.Player;
import components.rooms.WorldRoom;
import core.GamePanel;
import utilities.MapHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class World {

    private final Player player;                // PLAYER OBJECT

    private WorldRoom currentRoom;              // ROOM THAT PLAYER IS IN
    private WorldRoom loadingRoom;              // ROOM THAT PLAYER IS ENTERING

    private ArrayList<WorldRoom> roomBuffer;    // STORES THE STATE OF THE LAST 5 ROOMS

    private final int widthOfTile;              // STORES DIMENSIONS OF TILE
    private final int heightOfTile;

    private final MapHandler mapHandler;        // MAP HELPER TO CONSTRUCT WORLD

    // TODO CREATE METADATA FOR THE WORLD
    public World(int startingRoom, String tileMapFilePath, String metadataFilePath, int columns, int rows) {

        player = new Player(this);

        widthOfTile = GamePanel.TILE_SIZE;
        heightOfTile = GamePanel.TILE_SIZE;

        mapHandler = new MapHandler(this, tileMapFilePath, columns, rows);

        currentRoom = new WorldRoom(startingRoom, this, mapHandler);
        loadingRoom = null;

        roomBuffer = new ArrayList<>(Arrays.asList(new WorldRoom[5]));
    }

    // UPDATE PLAYER AND ROOM DATA
    public void update() {

        currentRoom.update();

        // IF THERE IS A LOADING ROOM / PLAYER MOVES TO ANOTHER ROOM
        if(loadingRoom != null) {
            // UPDATE DRAW COORDINATES
            loadingRoom.updateDrawCoordinates();

            // IF THE LOADING ROOM HAS FINISHED MOVING, STOP THE ROOM THEN SET IT AS THE CURRENT ROOM
            if(loadingRoom.getDrawCoordinates()[0] % GamePanel.SCREEN_WIDTH == 0 &&
                    loadingRoom.getDrawCoordinates()[1] % GamePanel.SCREEN_HEIGHT == 0) {

                currentRoom.setDrawVector(0, 0);
                loadingRoom.setDrawVector(0, 0);

                currentRoom = loadingRoom;
                loadingRoom = null;
            }
        }

        player.update();

        // CHECK IF SCREEN IS TRANSITIONING
        if(!player.getState().equals("TRANSITION") && loadingRoom == null) {

            // IF PLAYER IS LEAVING THROUGH THE LEFT
            if(player.getX() <= 0 && player.getDirection() == Direction.LEFT) {

                loadNewRoom(new int[] {16, 0}, currentRoom.getId() - 10,
                        new int[] {-currentRoom.getRoomWidth(), 0});
            }

            // IF PLAYER IS LEAVING THROUGH THE RIGHT
            if(player.getX() >= currentRoom.getRoomWidth() && player.getDirection() == Direction.RIGHT) {

                loadNewRoom(new int[] {-16, 0}, currentRoom.getId() + 10,
                        new int[] {currentRoom.getRoomWidth(), 0});
            }

            // IF PLAYER IS LEAVING UPWARDS
            if(player.getY() <= 0 && player.getDirection() == Direction.UP) {

                loadNewRoom(new int[] {0, 12}, currentRoom.getId() - 1,
                        new int[] {0, -currentRoom.getRoomHeight()});
            }

            // IF PLAYER IS LEAVING DOWNWARDS
            if(player.getY() >= currentRoom.getRoomHeight() && player.getDirection() == Direction.DOWN) {

                loadNewRoom(new int[] {0, -12}, currentRoom.getId() + 1,
                        new int[] {0, currentRoom.getRoomHeight()});
            }
        }
    }

    // LOADS THE NEW ROOM FROM ANOTHER DIRECTION
    private void loadNewRoom(int[] transitionVector, int loadingRoomID, int[] loadingRoomLocation)
    {
        // SAVE THE CURRENT ROOM
        saveRoom();

        // SET TRANSITION VECTOR SO THAT PLAYER MOVES WITH THE ROOM
        player.setTransitionVector(transitionVector[0], transitionVector[1]);

        // CHECK IF THE LOADED ROOM IS A STORED ROOM
        int roomFoundIndex = -1;

        for(int i = 0; i < 5; i++) {

            if(roomBuffer.get(i) != null) {

                if(roomBuffer.get(i).getId() == loadingRoomID) {

                    roomFoundIndex = i;
                    break;
                }
            }
        }

        // IF THERE IS NO ROOM FOUND
        if(roomFoundIndex == -1) {
            loadingRoom = new WorldRoom(loadingRoomID, this, mapHandler);
        } else { // IF THERE IS, THEN SET IT AS THE LOADING SCREEN
            loadingRoom = roomBuffer.get(roomFoundIndex);
        }

        // SET THE COORDINATES FOR THE ROOM TO MOVE
        loadingRoom.setDrawCoordinates(loadingRoomLocation[0], loadingRoomLocation[1]);
        currentRoom.setDrawVector(transitionVector[0], transitionVector[1]);
        loadingRoom.setDrawVector(transitionVector[0], transitionVector[1]);

        currentRoom.updateDrawCoordinates();
        loadingRoom.updateDrawCoordinates();
    }


    // DRAW THE WORLD
    public void draw(Graphics2D g2) {

        currentRoom.draw(g2);

        if(loadingRoom != null) {
            loadingRoom.draw(g2);
        }

        player.draw(g2);
    }

    // SAVES THE PREVIOUS 5 ROOMS INTO THE ROOM BUFFER
    private void saveRoom() {

        roomBuffer.addFirst(currentRoom);
        if(roomBuffer.size() > 5) roomBuffer.remove(4);
    }

    public WorldRoom getCurrentRoom()
    {
        return currentRoom;
    }
    public WorldRoom getLoadingRoom()
    {
        return loadingRoom;
    }

    public Player getPlayer() { return player; }
    public MapHandler getMapHelper()
    {
        return mapHandler;
    }

    // CHECK IF IN TRANSITION PHASE
    public boolean isTransitioning() {

        return (currentRoom.getDrawVelocity()[0] != 0 && currentRoom.getDrawVelocity()[1] != 0)
                && (loadingRoom != null);
    }
}
