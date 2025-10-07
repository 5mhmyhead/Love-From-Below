package components.rooms;

import components.entity.Player;
import components.objects.WorldObject;
import components.world.World;
import core.GamePanel;
import utilities.MapHandler;
import utilities.Tile;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class WorldRoom implements Room {

    private final int id;           // COLUMN/ROW OF THE ROOM IN THE WORLD

    private int drawX, drawY;       // WHERE THE ROOM SHOULD BE DRAWN
    private int drawVelX, drawVelY; // VELOCITY FOR THE DrawX/DrawY COORDINATES

    // ROOM VARIABLES
    private final int numOfColumns, numOfRows;
    private final int widthOfTile, heightOfTile;
    private final int roomWidth, roomHeight;

    // ENTITY VARIABLES
    private final Player player;

    private final ArrayList<WorldObject> worldObjects;

    // HELPER CLASSES
    private final MapHandler mapHandler;
    private RoomMetadata roomMetadata;

    // ROOM TILES
    private final Tile[][] tiles;

    // CONSTRUCTOR
    public WorldRoom(int id, World world, MapHandler mapHandler) {

        this.id = id;

        drawX = 0;
        drawY = 0;

        drawVelX = 0;
        drawVelY = 0;

        this.numOfColumns = GamePanel.MAX_SCREEN_COL;
        this.numOfRows = GamePanel.MAX_SCREEN_ROW;

        this.widthOfTile = GamePanel.TILE_SIZE;
        this.heightOfTile = GamePanel.TILE_SIZE;

        this.roomWidth = GamePanel.SCREEN_WIDTH;
        this.roomHeight = GamePanel.SCREEN_HEIGHT;

        this.mapHandler = mapHandler;
        tiles = new Tile[numOfColumns][numOfRows];

        player = world.getPlayer();
        worldObjects = new ArrayList<>();

        loadTiles();
    }

    // GOES THROUGH THE WORLD TILES
    // THEN GETS SECTION THAT BELONGS TO THIS ROOM
    private void loadTiles() {
        // THE ID HAS TWO DIGITS, THE FIRST FOR THE COLUMN AND THE SECOND FOR ROW
        int screenColumn = (int) Math.floor((double) id / 10);  // RETURNS THE TENTHS DIGIT: COLUMN
        int screenRow = id % 10;                                // RETURNS THE ONES DIGIT: ROW

        for(int column = (screenColumn - 1) * numOfColumns; column < screenColumn * numOfColumns; column++) {

            for(int row = (screenRow - 1) * numOfRows; row < screenRow * numOfRows; row++) {
                // GETS THE TILE AS STRING
                String tile = mapHandler.getTile(column, row);
                // THEN PARSES IT TO INT
                tiles[column - ((screenColumn - 1) * numOfColumns)]
                        [row - (screenRow - 1) * numOfRows] =
                        Tile.parseTileID(Integer.parseInt(tile));
            }
        }
    }

    // SET THE ROOM METADATA
    public void setRoomMetadata(RoomMetadata roomMetadata) {

        this.roomMetadata = roomMetadata;
        this.worldObjects.addAll(roomMetadata.getWorldObjects());
    }

    // UPDATE OBJECTS IN THE ROOM
    public void update() {
        updateDrawCoordinates();
    }

    // DRAWS THE ROOM
    public void draw(Graphics2D g2) {

        AffineTransform transform = g2.getTransform();
        g2.translate(drawX, drawY);

        for(int col = 0; col < numOfColumns; col++) {

            for(int row = 0; row < numOfRows; row++) {

                g2.drawImage(Tile.getTileSprite(tiles[col][row]), widthOfTile * col, heightOfTile * row,
                        widthOfTile, heightOfTile, null);
            }
        }

        for(WorldObject object : worldObjects) {
            object.draw(g2);
        }

        g2.setTransform(transform);
        // FIXME REMOVE DEBUG WHEN FINISHED
        drawTileDebug(g2);
        drawObjectDebug(g2);
    }

    // UPDATES DRAW POSITION OF ROOM
    public void updateDrawCoordinates() {

        drawX += drawVelX;
        drawY += drawVelY;
    }

    // SET DRAW POSITION OF ROOM
    public void setDrawCoordinates(int drawX, int drawY) {

        this.drawX = drawX;
        this.drawY = drawY;
    }

    // SET VELOCITY OF ROOM
    public void setDrawVector(int drawVelX, int drawVelY) {

        this.drawVelX = drawVelX;
        this.drawVelY = drawVelY;
    }

    public int[] getDrawCoordinates()
    {
        return new int[] {drawX, drawY};
    }
    public int[] getDrawVelocity()
    {
        return new int[] {drawVelX, drawVelY};
    }

    @Override
    public int getRoomWidth() { return roomWidth; }
    public int getRoomHeight() { return roomHeight; }

    public int getNumOfColumns() { return numOfColumns; }
    public int getNumOfRows() { return numOfRows; }

    public int getWidthOfTile() { return widthOfTile; }
    public int getHeightOfTile() { return heightOfTile; }

    public int getId() { return id; }
    public Tile getTile(int column, int row) { return tiles[column][row]; }

    public Player getPlayer() { return player; }

    // DEBUG TO DRAW COLLISIONS OF TILES
    private void drawTileDebug(Graphics2D g2) {

        for(int i = 0; i < numOfColumns; i++) {

            for(int j = 0; j < numOfRows; j++) {

                if(tiles[i][j].hasTileCollision()) {

                    g2.setColor(new Color(0, 255, 0, 100));

                    Rectangle tileRectangle = new Rectangle(i * this.getWidthOfTile(),
                            j * this.getWidthOfTile(), this.getWidthOfTile(),
                            this.getHeightOfTile() / 2);

                    g2.fill(tileRectangle);
                }
            }
        }
    }

    private void drawObjectDebug(Graphics2D g2) {

        ArrayList<WorldObject> worldObjects = roomMetadata.getWorldObjects();

        for(WorldObject object : worldObjects) {

            g2.setColor(new Color(0, 0, 255, 100));

            Rectangle tileRectangle = new Rectangle(object.getX(),
                    object.getY(), object.getWidth(), object.getHeight());

            g2.fill(tileRectangle);
        }
    }
}
