package utilities;

import components.entities.Direction;
import components.entities.Enemy;
import components.entities.NPC;
import components.entities.enemies.Slime;
import components.entities.npcs.Flerp;
import components.objects.collectibles.Boots;
import components.objects.collectibles.Candle;
import components.objects.interactables.Bush;
import components.objects.interactables.CandleBush;
import components.objects.interactables.NormalChest;
import components.objects.WorldObject;
import components.objects.weapons.Sword;
import components.world.rooms.Room;
import components.world.World;
import core.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

// CLASS THAT HANDLES TILE, ENEMIES, AND SWITCHING MAPS
// THIS CLASS IS INSTANTIATED BY THE WORLD CLASS FOR LOADING TILES AND RETURNING THEM TO THE WORLD
public class MapHandler {

    private final World world;
    private final String[][] worldTiles;

    public MapHandler(World world, String tileMapFilePath, int columns, int rows) {

        this.world = world;
        worldTiles = new String[columns][rows];

        loadTiles(tileMapFilePath);
    }

    private void loadTiles(String filePath) {
        // BUFFERED READER TO READ TILEMAPS
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(filePath)),
                        Charset.defaultCharset()))
        ) {

            String line;
            int lineCount = 0;

            while((line = bufferedReader.readLine()) != null) {

                StringTokenizer tokenizer = new StringTokenizer(line);
                int characterCount = 0;

                while(tokenizer.hasMoreElements()) {

                    String token = tokenizer.nextToken();
                    worldTiles[characterCount][lineCount] = token;
                    characterCount++;
                }

                lineCount++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // RETURNS THE TILE FOR THE GIVEN COORDINATES
    public String getTile(int column, int row) {
        return worldTiles[column][row];
    }

    // BUILDS AND PUTS EVERY OBJECT IN THE WORLD
    public WorldObject buildObject(String id, ArrayList<String[]> text, int col, int row) {
        // FIND THE COORDINATES OF THE OBJECT
        int x = col * GamePanel.TILE_SIZE;
        int y = row * GamePanel.TILE_SIZE;

        // CHECK IF WE LOAD THE OBJECT IN THE CURRENT ROOM OR THE LOADING ROOM
        Room room = (world.getLoadingRoom() != null) ?
                world.getLoadingRoom() : world.getCurrentRoom();

        return switch(id) {

            case "NORMAL_CHEST" -> new NormalChest(x, y, room);

            case "CANDLE" -> new Candle(x, y, room);
            case "BOOTS" -> new Boots(x, y, room);
            case "SWORD" -> new Sword(x, y, room);

            case "CANDLE_BUSH" -> new CandleBush(x, y, room);
            case "BUSH" -> new Bush(x, y, text, room);

            default -> null;
        };
    }

    public NPC buildNPC(String id, ArrayList<String[]> dialogue, int col, int row) {
        // FIND THE COORDINATES OF THE NPC
        int x = col * GamePanel.TILE_SIZE;
        int y = row * GamePanel.TILE_SIZE;

        // CHECK IF WE LOAD THE NPC IN THE CURRENT ROOM OR THE LOADING ROOM
        Room room = (world.getLoadingRoom() != null) ?
                world.getLoadingRoom() : world.getCurrentRoom();

        return switch(id) {

            case "FLERP" -> new Flerp(x, y, dialogue, room);

            default -> null;
        };
    }

    public Enemy buildEnemy(String id, int col, int row) {
        // FIND THE COORDINATES OF THE ENEMY
        int x = col * GamePanel.TILE_SIZE;
        int y = row * GamePanel.TILE_SIZE;

        // CHECK IF WE LOAD THE NPC IN THE CURRENT ROOM OR THE LOADING ROOM
        Room room = (world.getLoadingRoom() != null) ?
                world.getLoadingRoom() : world.getCurrentRoom();

        return switch(id) {

            case "SLIME" -> new Slime(x, y, Direction.getRandom(), room);

            default -> null;
        };
    }
}

