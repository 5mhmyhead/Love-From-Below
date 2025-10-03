package components.rooms;

import components.entity.Player;
import components.world.World;
import core.GamePanel;
import utilities.MapHandler;
import utilities.Tile;

import java.awt.*;
import java.awt.geom.AffineTransform;

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

    // HELPER CLASSES
    private final MapHandler mapHandler;

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

        tiles = new Tile[numOfColumns][numOfRows];
        this.mapHandler = mapHandler;

        player = world.getPlayer();

        loadTiles();
    }

    // GOES THROUGH THE WORLD TILES
    // THEN GETS SECTION THAT BELONGS TO THIS ROOM
    private void loadTiles() {
        // THE ID HAS TWO DIGITS, THE FIRST FOR THE COLUMN AND THE SECOND FOR ROW
        int screenColumn = (int) Math.floor((double) id / 10);
        int screenRow = id % 10;

        for(int column = (screenColumn - 1) * numOfColumns; column < screenColumn * numOfColumns; column++) {

            for(int row = (screenRow - 1) * numOfRows; row < screenRow * numOfRows; row++) {
                // GETS THE TILE AS STRING
                String tile = mapHandler.getTile(column, row);
                // THEN PARSES IT TO INT
                tiles[column - ((screenColumn - 1) * numOfColumns)]
                        [row - (screenRow - 1) * numOfRows] =
                        Tile.parseID(Integer.parseInt(tile));
            }
        }
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

                g2.drawImage(Tile.getSprite(tiles[col][row]), widthOfTile * col, heightOfTile * row,
                        widthOfTile, heightOfTile, null);
            }
        }

        g2.setTransform(transform);
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
}
