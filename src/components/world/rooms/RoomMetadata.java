package components.world.rooms;

import components.entities.Enemy;
import components.entities.NPC;
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
    private ArrayList<NPC> worldNPCS;
    private ArrayList<Enemy> worldEnemies;

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
            worldEnemies = new ArrayList<>();

            // GOES THROUGH ALL OBJECTS
            Element objectsElement = (Element) thisRoom.getElementsByTagName("OBJECTS").item(0);
            NodeList objectsList = objectsElement.getElementsByTagName("OBJECT");

            for(int objectIndex = 0; objectIndex < objectsList.getLength(); objectIndex++) {

                Element object = (Element) objectsList.item(objectIndex);
                String type = object.getElementsByTagName("TYPE").item(0).getTextContent();

                // CREATE AN ARRAY LIST OF DIALOGUES
                ArrayList<String[]> text = new ArrayList<>();
                NodeList textList = object.getElementsByTagName("TEXT");

                for(int textIndex = 0; textIndex < textList.getLength(); textIndex++) {
                    // ADD EACH TEXT PART TO THE ARRAY OF TEXT
                    Element textElement = (Element) textList.item(textIndex);
                    String temp = textElement.getTextContent();

                    // SPLIT THE TEXT DIVIDED BY A SEMICOLON
                    String[] parts = temp.split(";");
                    // THEN ADD IT TO THE ARRAY OF TEXT
                    text.add(parts);
                }

                int col = Integer.parseInt(object.getElementsByTagName("COL").item(0).getTextContent());
                int row = Integer.parseInt(object.getElementsByTagName("ROW").item(0).getTextContent());

                worldObjects.add(world.getMapHandler().buildObject(type, text, col, row));
            }

            // GOES THROUGH ALL NPCS
            Element npcElement = (Element) thisRoom.getElementsByTagName("NPCS").item(0);
            NodeList npcList = npcElement.getElementsByTagName("NPC");

            for(int npcIndex = 0; npcIndex < npcList.getLength(); npcIndex++) {

                Element npc = (Element) npcList.item(npcIndex);
                String name = npc.getElementsByTagName("NAME").item(0).getTextContent();

                // CREATE AN ARRAY LIST OF DIALOGUES
                ArrayList<String[]> dialogue = new ArrayList<>();
                NodeList dialogueList = npc.getElementsByTagName("DIALOGUE");

                for(int dialogueIndex = 0; dialogueIndex < dialogueList.getLength(); dialogueIndex++) {
                    // ADD EACH DIALOGUE PART TO THE ARRAY OF DIALOGUES
                    Element dialogueElement = (Element) dialogueList.item(dialogueIndex);
                    String dialogueText = dialogueElement.getTextContent();

                    // SPLIT THE TEXT DIALOGUE DIVIDED BY A SEMICOLON
                    String[] text = dialogueText.split(";");
                    // THEN ADD IT TO THE ARRAY LIST DIALOGUE
                    dialogue.add(text);
                }

                int col = Integer.parseInt(npc.getElementsByTagName("COL").item(0).getTextContent());
                int row = Integer.parseInt(npc.getElementsByTagName("ROW").item(0).getTextContent());

                worldNPCS.add(world.getMapHandler().buildNPC(name, dialogue, col, row));
            }

            // GOES THROUGH ALL ENEMIES
            Element enemyElement = (Element) thisRoom.getElementsByTagName("ENEMIES").item(0);
            NodeList enemyList = enemyElement.getElementsByTagName("ENEMY");

            for(int enemyIndex = 0; enemyIndex < enemyList.getLength(); enemyIndex++) {

                Element enemy = (Element) enemyList.item(enemyIndex);
                String name = enemy.getElementsByTagName("NAME").item(0).getTextContent();

                int col = Integer.parseInt(enemy.getElementsByTagName("COL").item(0).getTextContent());
                int row = Integer.parseInt(enemy.getElementsByTagName("ROW").item(0).getTextContent());

                worldEnemies.add(world.getMapHandler().buildEnemy(name, col, row));
            }
        }
    }

    public ArrayList<WorldObject> getWorldObjects() { return worldObjects; }
    public ArrayList<NPC> getWorldNPCS() { return worldNPCS; }
    public ArrayList<Enemy> getWorldEnemies() { return worldEnemies; }
}
