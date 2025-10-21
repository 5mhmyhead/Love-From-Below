package components.world;

import components.entity.Direction;
import components.entity.Player;
import components.world.rooms.RoomMetadata;
import components.world.rooms.WorldRoom;
import core.GamePanel;
import org.w3c.dom.Document;
import utilities.MapHandler;
import utilities.SoundManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class World {

    private final Player player;                            // PLAYER OBJECT

    private WorldRoom currentRoom;                          // ROOM THAT PLAYER IS IN
    private WorldRoom loadingRoom;                          // ROOM THAT PLAYER IS ENTERING

    private final ArrayList<WorldRoom> roomBuffer;          // STORES THE STATE OF THE LAST 5 ROOMS
    private final ArrayList<RoomMetadata> metadataBuffer;   // STORES THE METADATA OF THE LAST 5 ROOMS

    private final MapHandler mapHandler;                    // MAP HELPER TO CONSTRUCT WORLD

    private RoomMetadata roomMetadata;                      // THE ACTUAL METADATA OF THE ROOM
    private Document metadata;                              // XML DOCUMENT STORING ROOM INFO

    public World(int startingRoom, String tileMapFilePath, String RoomMetadataFilePath, int columns, int rows) {

        mapHandler = new MapHandler(this, tileMapFilePath, columns, rows);
        loadRoomMetadata(RoomMetadataFilePath);

        roomMetadata = new RoomMetadata(startingRoom, this);

        currentRoom = new WorldRoom(startingRoom, this, mapHandler);
        currentRoom.setRoomMetadata(roomMetadata);

        loadingRoom = null;

        player = new Player(this, roomMetadata);

        roomBuffer = new ArrayList<>(Arrays.asList(new WorldRoom[5]));
        metadataBuffer = new ArrayList<>(Arrays.asList(new RoomMetadata[5]));

        // FIXME TURNED OFF MUSIC
        // if(!SoundManager.OVERWORLD.isPlaying()) SoundManager.OVERWORLD.loop();
    }

    // LOADS THE METADATA FROM PATH
    private void loadRoomMetadata(String filePath) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        factory.setNamespaceAware(true);

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();
            metadata = builder.parse(getClass().getResourceAsStream(filePath));
        } catch(Exception e) {
            e.printStackTrace();
        }

        metadata.getDocumentElement().normalize();
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
            // PLAYER X AND Y IS IN THE TOP RIGHT CORNER
            // SO WE MOVE IT TO THE CENTER OF THE PLAYER SPRITE
            int centeredX = player.getX() + player.getWidth() / 2;
            int centeredY = player.getY() + player.getHeight() / 2;

            // IF PLAYER IS LEAVING THROUGH THE LEFT
            if(centeredX <= 0 && player.getDirection() == Direction.LEFT) {

                loadNewRoom(new int[] {16, 0}, currentRoom.getId() - 10,
                        new int[] {-currentRoom.getRoomWidth(), 0});
            }

            // IF PLAYER IS LEAVING THROUGH THE RIGHT
            if(centeredX >= currentRoom.getRoomWidth() && player.getDirection() == Direction.RIGHT) {

                loadNewRoom(new int[] {-16, 0}, currentRoom.getId() + 10,
                        new int[] {currentRoom.getRoomWidth(), 0});
            }

            // IF PLAYER IS LEAVING UPWARDS
            if(centeredY <= 0 && player.getDirection() == Direction.UP) {

                loadNewRoom(new int[] {0, 12}, currentRoom.getId() - 1,
                        new int[] {0, -currentRoom.getRoomHeight()});
            }

            // IF PLAYER IS LEAVING DOWNWARDS
            if(centeredY >= currentRoom.getRoomHeight() && player.getDirection() == Direction.DOWN) {

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

            roomMetadata = new RoomMetadata(loadingRoomID, this);

            loadingRoom = new WorldRoom(loadingRoomID, this, mapHandler);
            loadingRoom.setRoomMetadata(roomMetadata);
        } else {
            // IF THERE IS, THEN SET IT AS THE LOADING SCREEN
            roomMetadata = metadataBuffer.get(roomFoundIndex);

            loadingRoom = roomBuffer.get(roomFoundIndex);
            loadingRoom.setRoomMetadata(roomMetadata);
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

        metadataBuffer.addFirst(roomMetadata);
        if(metadataBuffer.size() > 5) metadataBuffer.remove(4);
    }

    public WorldRoom getCurrentRoom()
    {
        return currentRoom;
    }
    public WorldRoom getLoadingRoom()  {return loadingRoom; }

    public Player getPlayer() { return player; }
    public MapHandler getMapHandler()
    {
        return mapHandler;
    }

    public Document getMetadata() { return metadata; }
    public RoomMetadata getRoomMetadata() { return roomMetadata; }

    // CHECK IF IN TRANSITION PHASE
    public boolean isTransitioning() {

        return (currentRoom.getDrawVelocity()[0] != 0 && currentRoom.getDrawVelocity()[1] != 0)
                && (loadingRoom != null);
    }
}
