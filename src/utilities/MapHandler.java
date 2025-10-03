package utilities;

import components.world.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.StringTokenizer;

// CLASS THAT HANDLE TILE, ENEMIES, AND SWITCHING MAPS
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
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(filePath))
                        , Charset.defaultCharset()))
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
}

