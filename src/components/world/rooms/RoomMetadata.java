package components.world.rooms;

import components.entity.Entity;
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
    private ArrayList<Entity> worldNPCS;

    private String roomType;
    private String music;

    public RoomMetadata(int id, World world) {

        this.id = id;
        this.world = world;

        loadMetadata();
    }

    // READS AN XML FILE THROUGH THE ID GIVEN
    private void loadMetadata() {
        // GRABS THE METADATA DOCUMENT
        Document metadata = world.getMetadata();

        // GETS ALL ROOMS AND FINDS THE MATCHING ID
        Element thisRoom = null;
        NodeList rooms = metadata.getElementsByTagName("ROOM");

        for(int roomIndex = 0; roomIndex < rooms.getLength(); roomIndex++) {

            Element room = (Element) rooms.item(roomIndex);

            if(room.getAttribute("id").equals(id / 10 + "-" + id % 10)) {

                thisRoom = room;
                break;
            }
        }

        if(thisRoom != null) {

            // GRABS BASIC ROOM DATA
            roomType = thisRoom.getElementsByTagName("ROOM-TYPE").item(0).getTextContent();
            music = thisRoom.getElementsByTagName("MUSIC").item(0).getTextContent();

            worldObjects = new ArrayList<>();
            worldNPCS = new ArrayList<>();

            // GOES THROUGH ALL OBJECTS
            Element objectsElement = (Element) thisRoom.getElementsByTagName("OBJECTS").item(0);
            NodeList objectsList = objectsElement.getElementsByTagName("OBJECT");

            for(int objectIndex = 0; objectIndex < objectsList.getLength(); objectIndex++) {

                Element object = (Element) objectsList.item(objectIndex);
                String type = object.getElementsByTagName("TYPE").item(0).getTextContent();

                int col = Integer.parseInt(object.getElementsByTagName("COL").item(0).getTextContent());
                int row = Integer.parseInt(object.getElementsByTagName("ROW").item(0).getTextContent());

                worldObjects.add(world.getMapHandler().buildObject(type, col, row));
            }

            // GOES THROUGH ALL NPCS
            Element npcElement = (Element) thisRoom.getElementsByTagName("NPCS").item(0);
            NodeList npcList = npcElement.getElementsByTagName("NPC");

            for(int npcIndex = 0; npcIndex < npcList.getLength(); npcIndex++) {

                Element npc = (Element) npcList.item(npcIndex);
                String name = npc.getElementsByTagName("NAME").item(0).getTextContent();
                String dialogue = npc.getElementsByTagName("DIALOGUE").item(0).getTextContent();

                int col = Integer.parseInt(npc.getElementsByTagName("COL").item(0).getTextContent());
                int row = Integer.parseInt(npc.getElementsByTagName("ROW").item(0).getTextContent());
                // SPLIT THE TEXT DIALOGUE DIVIDED BY A SEMICOLON
                String[] text = dialogue.split(";");

                worldNPCS.add(world.getMapHandler().buildNPC(name, text, col, row));
            }
        }
    }

    public ArrayList<WorldObject> getWorldObjects() { return worldObjects; }
    public ArrayList<Entity> getWorldNPCS() { return worldNPCS; }
}
