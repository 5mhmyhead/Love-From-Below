package utilities;

import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Images {

    // LOADS AN IMAGE FROM A PATH
    private static BufferedImage loadSingleImage(String path) {

        try {
            return ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream(path)));
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ALL ASSETS FOR TITLE SCREEN
    public static class TitleScreenAssets {
        public static final BufferedImage TITLE_SCREEN = loadSingleImage("/assets/titleScreen/titleScreen.png");
    }

    // ALL SPRITES FOR THE PLAYER
    public static class PlayerAssets {
        // MOVEMENT SPRITES
        public static final BufferedImage PLAYER_MOVEMENT = loadSingleImage("/assets/entities/player/playerMovement.png");

        public static final BufferedImage PLAYER_DOWN = loadSingleImage("/assets/entities/player/playerDown.png");
        public static final BufferedImage PLAYER_UP = loadSingleImage("/assets/entities/player/playerUp.png");

        public static final BufferedImage PLAYER_WALK_LEFT = loadSingleImage("/assets/entities/player/playerWalkLeft.png");
        public static final BufferedImage PLAYER_WALK_RIGHT = loadSingleImage("/assets/entities/player/playerWalkRight.png");

        public static final BufferedImage PLAYER_RUN_LEFT = loadSingleImage("/assets/entities/player/playerRunLeft.png");
        public static final BufferedImage PLAYER_RUN_RIGHT = loadSingleImage("/assets/entities/player/playerRunRight.png");
    }

    // ALL OBJECT ASSETS
    public static class WorldObjects {

        public static final BufferedImage NORMAL_CHEST_CLOSED = loadSingleImage("/assets/objects/nChestClosed.png");
        public static final BufferedImage NORMAL_CHEST_OPENED = loadSingleImage("/assets/objects/nChestOpened.png");

        public static final BufferedImage BOOTS = loadSingleImage("/assets/objects/boots.png");
    }

    // ALL TILE ASSETS
    public static class Tiles {
        // ALL GROUND TILES
        public static class Ground {
            // ALL GRASS TILES
            public static class Grass {

                public static final BufferedImage GRASS_1 = loadSingleImage("/assets/tiles/ground/grass/grass1.png");
                public static final BufferedImage GRASS_2 = loadSingleImage("/assets/tiles/ground/grass/grass2.png");
                public static final BufferedImage GRASS_3 = loadSingleImage("/assets/tiles/ground/grass/grass3.png");
                public static final BufferedImage GRASS_4 = loadSingleImage("/assets/tiles/ground/grass/grass4.png");
                public static final BufferedImage GRASS_5 = loadSingleImage("/assets/tiles/ground/grass/grass5.png");
                public static final BufferedImage GRASS_6 = loadSingleImage("/assets/tiles/ground/grass/grass6.png");
                public static final BufferedImage GRASS_7 = loadSingleImage("/assets/tiles/ground/grass/grass7.png");
                public static final BufferedImage GRASS_8 = loadSingleImage("/assets/tiles/ground/grass/grass8.png");
                public static final BufferedImage GRASS_9 = loadSingleImage("/assets/tiles/ground/grass/grass9.png");
                public static final BufferedImage GRASS_10 = loadSingleImage("/assets/tiles/ground/grass/grass10.png");
                public static final BufferedImage GRASS_11 = loadSingleImage("/assets/tiles/ground/grass/grass11.png");
                public static final BufferedImage GRASS_12 = loadSingleImage("/assets/tiles/ground/grass/grass12.png");
                public static final BufferedImage GRASS_13 = loadSingleImage("/assets/tiles/ground/grass/grass13.png");
                public static final BufferedImage GRASS_14 = loadSingleImage("/assets/tiles/ground/grass/grass14.png");
                public static final BufferedImage GRASS_15 = loadSingleImage("/assets/tiles/ground/grass/grass15.png");
                public static final BufferedImage GRASS_16 = loadSingleImage("/assets/tiles/ground/grass/grass16.png");
                public static final BufferedImage GRASS_17 = loadSingleImage("/assets/tiles/ground/grass/grass17.png");
                public static final BufferedImage GRASS_18 = loadSingleImage("/assets/tiles/ground/grass/grass18.png");
                public static final BufferedImage GRASS_19 = loadSingleImage("/assets/tiles/ground/grass/grass19.png");
                public static final BufferedImage GRASS_20 = loadSingleImage("/assets/tiles/ground/grass/grass20.png");
                public static final BufferedImage GRASS_21 = loadSingleImage("/assets/tiles/ground/grass/grass21.png");
                public static final BufferedImage GRASS_22 = loadSingleImage("/assets/tiles/ground/grass/grass22.png");
                public static final BufferedImage GRASS_23 = loadSingleImage("/assets/tiles/ground/grass/grass23.png");
                public static final BufferedImage GRASS_24 = loadSingleImage("/assets/tiles/ground/grass/grass24.png");
                public static final BufferedImage GRASS_25 = loadSingleImage("/assets/tiles/ground/grass/grass25.png");
                public static final BufferedImage GRASS_26 = loadSingleImage("/assets/tiles/ground/grass/grass26.png");
                public static final BufferedImage GRASS_27 = loadSingleImage("/assets/tiles/ground/grass/grass27.png");
                public static final BufferedImage GRASS_28 = loadSingleImage("/assets/tiles/ground/grass/grass28.png");
                public static final BufferedImage GRASS_29 = loadSingleImage("/assets/tiles/ground/grass/grass29.png");
                public static final BufferedImage GRASS_30 = loadSingleImage("/assets/tiles/ground/grass/grass30.png");
                public static final BufferedImage GRASS_31 = loadSingleImage("/assets/tiles/ground/grass/grass31.png");
                public static final BufferedImage GRASS_32 = loadSingleImage("/assets/tiles/ground/grass/grass32.png");
                public static final BufferedImage GRASS_33 = loadSingleImage("/assets/tiles/ground/grass/grass33.png");
                public static final BufferedImage GRASS_34 = loadSingleImage("/assets/tiles/ground/grass/grass34.png");
                public static final BufferedImage GRASS_35 = loadSingleImage("/assets/tiles/ground/grass/grass35.png");
                public static final BufferedImage GRASS_36 = loadSingleImage("/assets/tiles/ground/grass/grass36.png");
                public static final BufferedImage GRASS_37 = loadSingleImage("/assets/tiles/ground/grass/grass37.png");
                public static final BufferedImage GRASS_38 = loadSingleImage("/assets/tiles/ground/grass/grass38.png");
                public static final BufferedImage GRASS_39 = loadSingleImage("/assets/tiles/ground/grass/grass39.png");
                public static final BufferedImage GRASS_40 = loadSingleImage("/assets/tiles/ground/grass/grass40.png");
                public static final BufferedImage GRASS_41 = loadSingleImage("/assets/tiles/ground/grass/grass41.png");
                public static final BufferedImage GRASS_42 = loadSingleImage("/assets/tiles/ground/grass/grass42.png");
                public static final BufferedImage GRASS_43 = loadSingleImage("/assets/tiles/ground/grass/grass43.png");
                public static final BufferedImage GRASS_44 = loadSingleImage("/assets/tiles/ground/grass/grass44.png");
                public static final BufferedImage GRASS_45 = loadSingleImage("/assets/tiles/ground/grass/grass45.png");
                public static final BufferedImage GRASS_46 = loadSingleImage("/assets/tiles/ground/grass/grass46.png");
                public static final BufferedImage GRASS_47 = loadSingleImage("/assets/tiles/ground/grass/grass47.png");
            }
        }

        // ALL WALL TILES
        public static class Walls {
            // ALL CAVE TILES
            public static class Cave {

                public static final BufferedImage CAVE_WALL_1 = loadSingleImage("/assets/tiles/caves/caveWalls1.png");
                public static final BufferedImage CAVE_WALL_2 = loadSingleImage("/assets/tiles/caves/caveWalls2.png");
                public static final BufferedImage CAVE_WALL_3 = loadSingleImage("/assets/tiles/caves/caveWalls3.png");
                public static final BufferedImage CAVE_WALL_4 = loadSingleImage("/assets/tiles/caves/caveWalls4.png");
                public static final BufferedImage CAVE_WALL_5 = loadSingleImage("/assets/tiles/caves/caveWalls5.png");
                public static final BufferedImage CAVE_WALL_6 = loadSingleImage("/assets/tiles/caves/caveWalls6.png");
                public static final BufferedImage CAVE_WALL_7 = loadSingleImage("/assets/tiles/caves/caveWalls7.png");
                public static final BufferedImage CAVE_WALL_8 = loadSingleImage("/assets/tiles/caves/caveWalls8.png");
                public static final BufferedImage CAVE_WALL_9 = loadSingleImage("/assets/tiles/caves/caveWalls9.png");
                public static final BufferedImage CAVE_WALL_10 = loadSingleImage("/assets/tiles/caves/caveWalls10.png");
                public static final BufferedImage CAVE_WALL_11 = loadSingleImage("/assets/tiles/caves/caveWalls11.png");
                public static final BufferedImage CAVE_WALL_12 = loadSingleImage("/assets/tiles/caves/caveWalls12.png");
                public static final BufferedImage CAVE_WALL_13 = loadSingleImage("/assets/tiles/caves/caveWalls13.png");
                public static final BufferedImage CAVE_WALL_14 = loadSingleImage("/assets/tiles/caves/caveWalls14.png");
                public static final BufferedImage CAVE_WALL_15 = loadSingleImage("/assets/tiles/caves/caveWalls15.png");
                public static final BufferedImage CAVE_WALL_16 = loadSingleImage("/assets/tiles/caves/caveWalls16.png");
                public static final BufferedImage CAVE_WALL_17 = loadSingleImage("/assets/tiles/caves/caveWalls17.png");
                public static final BufferedImage CAVE_WALL_18 = loadSingleImage("/assets/tiles/caves/caveWalls18.png");
                public static final BufferedImage CAVE_WALL_19 = loadSingleImage("/assets/tiles/caves/caveWalls19.png");
                public static final BufferedImage CAVE_WALL_20 = loadSingleImage("/assets/tiles/caves/caveWalls20.png");
                public static final BufferedImage CAVE_WALL_21 = loadSingleImage("/assets/tiles/caves/caveWalls21.png");
                public static final BufferedImage CAVE_WALL_22 = loadSingleImage("/assets/tiles/caves/caveWalls22.png");
                public static final BufferedImage CAVE_WALL_23 = loadSingleImage("/assets/tiles/caves/caveWalls23.png");
                public static final BufferedImage CAVE_WALL_24 = loadSingleImage("/assets/tiles/caves/caveWalls24.png");
                public static final BufferedImage CAVE_WALL_25 = loadSingleImage("/assets/tiles/caves/caveWalls25.png");
                public static final BufferedImage CAVE_WALL_26 = loadSingleImage("/assets/tiles/caves/caveWalls26.png");
                public static final BufferedImage CAVE_WALL_27 = loadSingleImage("/assets/tiles/caves/caveWalls27.png");
                public static final BufferedImage CAVE_WALL_28 = loadSingleImage("/assets/tiles/caves/caveWalls28.png");
                public static final BufferedImage CAVE_WALL_29 = loadSingleImage("/assets/tiles/caves/caveWalls29.png");
                public static final BufferedImage CAVE_WALL_30 = loadSingleImage("/assets/tiles/caves/caveWalls30.png");
                public static final BufferedImage CAVE_WALL_31 = loadSingleImage("/assets/tiles/caves/caveWalls31.png");
                public static final BufferedImage CAVE_WALL_32 = loadSingleImage("/assets/tiles/caves/caveWalls32.png");
                public static final BufferedImage CAVE_WALL_33 = loadSingleImage("/assets/tiles/caves/caveWalls33.png");
                public static final BufferedImage CAVE_WALL_34 = loadSingleImage("/assets/tiles/caves/caveWalls34.png");
                public static final BufferedImage CAVE_WALL_35 = loadSingleImage("/assets/tiles/caves/caveWalls35.png");
            }
        }

        // SOLID TILES FOR THE 4 COLORS
        public static class Solid {

            public static final BufferedImage SOLID_1 = loadSingleImage("/assets/tiles/solid/solid1.png");
            public static final BufferedImage SOLID_2 = loadSingleImage("/assets/tiles/solid/solid2.png");
            public static final BufferedImage SOLID_3 = loadSingleImage("/assets/tiles/solid/solid3.png");
            public static final BufferedImage SOLID_4 = loadSingleImage("/assets/tiles/solid/solid4.png");
        }
    }
}


