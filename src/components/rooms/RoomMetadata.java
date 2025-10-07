package components.rooms;

import components.objects.WorldObject;
import components.world.World;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class RoomMetadata {

    private final World world;
    private final int id;

    private ArrayList<WorldObject> worldObjects;

    private String roomType;
    private String music;

    // CONSTRUCTOR
    public RoomMetadata(int id, World world) {

        this.id = id;
        this.world = world;

        loadMetadata();
    }

    // READS AN XML FILE THROUGH THE ID GIVEN
    private void loadMetadata() {
        // GRABS THE METADATA DOCUMENT
        Document metadata = world.getMetadata();

        Element thisRoom = null;
        // GETS ALL ROOMS AND FINDS THE MATCHING ID
        NodeList rooms = metadata.getElementsByTagName("ROOM");

        for(int roomIndex = 0; roomIndex < rooms.getLength(); roomIndex++) {

            Element room = (Element) rooms.item(roomIndex);

            if(room.getAttribute("id").equals(id / 10 + "-" + id % 10)) {

                thisRoom = room;
                break;
            }
        }

        if(thisRoom != null) {

            // GRABS BASIC DATA
            roomType = thisRoom.getElementsByTagName("ROOM-TYPE").item(0).getTextContent();
            music = thisRoom.getElementsByTagName("MUSIC").item(0).getTextContent();

            worldObjects = new ArrayList<>();

            // GOES THROUGH ALL OBJECTS W/ MAP HANDLER
            Element objectsElement = (Element) thisRoom.getElementsByTagName("OBJECTS").item(0);
            NodeList objectsList = objectsElement.getElementsByTagName("OBJECT");

            for(int objectIndex = 0; objectIndex < objectsList.getLength(); objectIndex++) {

                Element object = (Element) objectsList.item(objectIndex);
                String type = object.getElementsByTagName("TYPE").item(0).getTextContent();

                int col = Integer.parseInt(object.getElementsByTagName("COL").item(0).getTextContent());
                int row = Integer.parseInt(object.getElementsByTagName("ROW").item(0).getTextContent());
                System.out.println("OBJECT " + objectIndex + "INITIALIZED");
                worldObjects.add(world.getMapHandler().buildObject(type, col, row));
            }
        }
    }

    public ArrayList<WorldObject> getWorldObjects() { return worldObjects; }
}
