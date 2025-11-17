package components.world.rooms;

import components.entities.Enemy;
import components.entities.Entity;
import components.entities.NPC;
import components.entities.Player;
import components.entities.enemies.Cloud;
import components.objects.WorldObject;
import components.objects.misc.AnimationObject;
import components.world.World;
import core.GamePanel;
import utilities.Animation;
import utilities.Images;
import utilities.MapHandler;
import utilities.Tile;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

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
    private final ArrayList<NPC> worldNPCS;
    private final ArrayList<Enemy> worldEnemies;

    // HELPER CLASSES
    private final MapHandler mapHandler;
    private RoomMetadata roomMetadata;

    // ROOM TILES
    private final Tile[][] tiles;

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
        worldNPCS = new ArrayList<>();
        worldEnemies = new ArrayList<>();

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
                        [row - (screenRow - 1) * numOfRows] = Tile.parseTileID(Integer.parseInt(tile));
            }
        }
    }

    // SET THE ROOM METADATA
    public void setRoomMetadata(RoomMetadata roomMetadata) {

        this.roomMetadata = roomMetadata;

        this.worldObjects.addAll(roomMetadata.getWorldObjects());
        this.worldNPCS.addAll(roomMetadata.getWorldNPCS());

        for(Enemy enemy : roomMetadata.getWorldEnemies())
            this.worldObjects.add(new Cloud((int) enemy.getX(), (int) enemy.getY(),this, enemy));
    }

    // UPDATE OBJECTS IN THE ROOM
    public void update() {

        updateDrawCoordinates();

        // IF THE ROOM IS NOT MOVING...
        if(drawVelX == 0 && drawVelY == 0) {
            // UPDATE ENEMIES
            Iterator<Enemy> enemyIterator = worldEnemies.iterator();

            while(enemyIterator.hasNext()) {

                Enemy enemy = enemyIterator.next();

                if(enemy.getStunTimer() == 0) enemy.update();
                else enemy.updateHealth();

                if(enemy.getDestroyFlag()) {
                    enemyIterator.remove();

                    // PLAY THE DEATH ANIMATION
                    assert Images.Enemies.DEATH != null;
                    worldObjects.add(new AnimationObject(
                            (int) Math.round(enemy.getX()),
                            (int) Math.round(enemy.getY()),
                            new Animation(3, false, Images.Enemies.DEATH,
                                    48, 48), this));
                }
            }

            // UPDATE WORLD OBJECTS
            Iterator<WorldObject> objectIterator = worldObjects.iterator();

            while(objectIterator.hasNext()) {

                WorldObject worldObject = objectIterator.next();

                worldObject.update();

                if(worldObject instanceof AnimationObject animationObject) {

                    if(animationObject.getAnimation().hasEnded()) {

                        objectIterator.remove();

                        if(animationObject instanceof Cloud cloud)
                            worldEnemies.add(cloud.getEnemy());
                    }
                }
            }
        }
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

        for(Enemy enemy : worldEnemies) { enemy.draw(g2); }
        for(WorldObject object : worldObjects) { object.draw(g2); }
        for(Entity npc : worldNPCS) { npc.draw(g2); }

        g2.setTransform(transform);

        //drawTileDebug(g2);
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

    public ArrayList<WorldObject> getWorldObjects() { return worldObjects; }
    public ArrayList<NPC> getWorldNPCS() { return worldNPCS; }
    public ArrayList<Enemy> getWorldEnemies() { return worldEnemies; }

    // DEBUG TO DRAW COLLISIONS OF TILES
    private void drawTileDebug(Graphics2D g2) {

        for(int i = 0; i < numOfColumns; i++) {

            for(int j = 0; j < numOfRows; j++) {

                if(tiles[i][j].hasTileCollision()) {

                    g2.setColor(new Color(0, 255, 0, 100));

                    Rectangle tileRectangle = new Rectangle(i * this.getWidthOfTile(),
                            j * this.getWidthOfTile(), this.getWidthOfTile(),
                            this.getHeightOfTile());

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
