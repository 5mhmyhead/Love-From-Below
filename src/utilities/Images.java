package utilities;

import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Images {

    private static final int TILE_WIDTH = GamePanel.TILE_SIZE;
    private static final int TILE_HEIGHT = GamePanel.TILE_SIZE;

    // LOADS AN IMAGE FROM A PATH
    private static BufferedImage loadSingleImage(String path) {

        try { return ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream(path))); }
        catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ALL ASSETS FOR TITLE SCREEN
    public static class TitleScreenAssets {

        public static final BufferedImage TITLE_SCREEN = loadSingleImage("/assets/titleScreen/titleScreen.png");
        public static final BufferedImage TITLE_CURSOR = loadSingleImage("/assets/titleScreen/titleCursor.png");

        public static final BufferedImage START_ENTERED = loadSingleImage("/assets/titleScreen/startEntered.png");
        public static final BufferedImage START_PICKED = loadSingleImage("/assets/titleScreen/startPicked.png");
        public static final BufferedImage START_UNPICKED = loadSingleImage("/assets/titleScreen/startUnpicked.png");

        public static final BufferedImage LOAD_ENTERED = loadSingleImage("/assets/titleScreen/loadEntered.png");
        public static final BufferedImage LOAD_PICKED = loadSingleImage("/assets/titleScreen/loadPicked.png");
        public static final BufferedImage LOAD_UNPICKED = loadSingleImage("/assets/titleScreen/loadUnpicked.png");

        public static final BufferedImage QUIT_ENTERED = loadSingleImage("/assets/titleScreen/quitEntered.png");
        public static final BufferedImage QUIT_PICKED = loadSingleImage("/assets/titleScreen/quitPicked.png");
        public static final BufferedImage QUIT_UNPICKED = loadSingleImage("/assets/titleScreen/quitUnpicked.png");
    }

    public static class Effects {

        public static final BufferedImage SPARKLE = loadSingleImage("/effects/sparkle.png");
        public static final BufferedImage BUSH_BREAK = loadSingleImage("/effects/break.png");
    }

    // ALL SPRITES FOR THE PLAYER
    public static class PlayerAssets {
        // MOVEMENT SPRITES
        public static final BufferedImage PLAYER_DOWN = loadSingleImage("/assets/entities/player/movement/playerDown.png");
        public static final BufferedImage PLAYER_UP = loadSingleImage("/assets/entities/player/movement/playerUp.png");

        public static final BufferedImage PLAYER_WALK_LEFT = loadSingleImage("/assets/entities/player/movement/playerWalkLeft.png");
        public static final BufferedImage PLAYER_WALK_RIGHT = loadSingleImage("/assets/entities/player/movement/playerWalkRight.png");

        public static final BufferedImage PLAYER_RUN_LEFT = loadSingleImage("/assets/entities/player/movement/playerRunLeft.png");
        public static final BufferedImage PLAYER_RUN_RIGHT = loadSingleImage("/assets/entities/player/movement/playerRunRight.png");

        public static final BufferedImage PLAYER_GET_ITEM = loadSingleImage("/assets/entities/player/playerGetItem.png");

        // ATTACK SPRITES
        public static final BufferedImage PLAYER_ATTACK_DOWN = loadSingleImage("/assets/entities/player/attack/attackDown.png");
        public static final BufferedImage PLAYER_ATTACK_UP = loadSingleImage("/assets/entities/player/attack/attackUp.png");
        public static final BufferedImage PLAYER_ATTACK_LEFT = loadSingleImage("/assets/entities/player/attack/attackLeft.png");
        public static final BufferedImage PLAYER_ATTACK_RIGHT = loadSingleImage("/assets/entities/player/attack/attackRight.png");
    }

    // ALL SPRITES FOR THE USER INTERFACE
    public static class UI {
        // DIALOGUE
        public static final BufferedImage DIALOGUE_BOX_TOP = loadSingleImage("/assets/ui/dialogueUI/dialogueBoxTop.png");
        public static final BufferedImage DIALOGUE_BOX_BOTTOM = loadSingleImage("/assets/ui/dialogueUI/dialogueBoxBottom.png");

        // TEXT
        public static final BufferedImage TEXT_BOX_TOP = loadSingleImage("/assets/ui/dialogueUI/textBoxTop.png");
        public static final BufferedImage TEXT_BOX_BOTTOM = loadSingleImage("/assets/ui/dialogueUI/textBoxBottom.png");

        // MENU
        public static final BufferedImage MENU_BOX = loadSingleImage("/assets/ui/menuUI/menuBox.png");

        public static final BufferedImage CONTROLS_PICKED = loadSingleImage("/assets/ui/menuUI/controlsPicked.png");
        public static final BufferedImage CONTROLS_UNPICKED = loadSingleImage("/assets/ui/menuUI/controlsUnpicked.png");

        public static final BufferedImage EXIT_GAME_ENTERED = loadSingleImage("/assets/ui/menuUI/exitGameEntered.png");
        public static final BufferedImage EXIT_GAME_PICKED = loadSingleImage("/assets/ui/menuUI/exitGamePicked.png");
        public static final BufferedImage EXIT_GAME_UNPICKED = loadSingleImage("/assets/ui/menuUI/exitGameUnpicked.png");

        public static final BufferedImage QUIT_MENU_ENTERED = loadSingleImage("/assets/ui/menuUI/quitToMenuEntered.png");
        public static final BufferedImage QUIT_MENU_PICKED = loadSingleImage("/assets/ui/menuUI/quitToMenuPicked.png");
        public static final BufferedImage QUIT_MENU_UNPICKED = loadSingleImage("/assets/ui/menuUI/quitToMenuUnpicked.png");

        public static final BufferedImage FULLSCREEN_ON_PICKED = loadSingleImage("/assets/ui/menuUI/fullscreenOnPicked.png");
        public static final BufferedImage FULLSCREEN_ON_UNPICKED = loadSingleImage("/assets/ui/menuUI/fullscreenOnUnpicked.png");

        public static final BufferedImage FULLSCREEN_OFF_PICKED = loadSingleImage("/assets/ui/menuUI/fullscreenOffPicked.png");
        public static final BufferedImage FULLSCREEN_OFF_UNPICKED = loadSingleImage("/assets/ui/menuUI/fullscreenOffUnpicked.png");

        public static final BufferedImage MUSIC_PICKED = loadSingleImage("/assets/ui/menuUI/musicPicked.png");
        public static final BufferedImage MUSIC_UNPICKED = loadSingleImage("/assets/ui/menuUI/musicUnpicked.png");

        public static final BufferedImage SOUND_PICKED = loadSingleImage("/assets/ui/menuUI/soundPicked.png");
        public static final BufferedImage SOUND_UNPICKED = loadSingleImage("/assets/ui/menuUI/soundUnpicked.png");

        public static final BufferedImage SOUND_BAR = loadSingleImage("/assets/ui/menuUI/soundBar.png");

        // GAME UI
        public static final BufferedImage HEALTH_BAR_PLAYER = loadSingleImage("/assets/ui/gameplayUI/healthBarPlayer.png");
        public static final BufferedImage HEART = loadSingleImage("/assets/ui/gameplayUI/heart.png");
        public static final BufferedImage HEART_EMPTY = loadSingleImage("/assets/ui/gameplayUI/heartEmpty.png");
    }

    public static class npcAssets {
        // SLEEPING GUARD FLERP
        public static final BufferedImage FLERP_PORTRAIT = loadSingleImage("/assets/entities/npcs/flerpPortrait.png");

        public static final BufferedImage FLERP_IDLE_SLEEP = loadSingleImage("/assets/entities/npcs/flerpIdleSleep.png");
        public static final BufferedImage FLERP_IDLE = loadSingleImage("/assets/entities/npcs/flerpIdle.png");

        public static final BufferedImage FLERP_WAKE_UP = loadSingleImage("/assets/entities/npcs/flerpWakeUp.png");
        public static final BufferedImage FLERP_FALL_ASLEEP = loadSingleImage("/assets/entities/npcs/flerpFallAsleep.png");
    }

    // ALL OBJECT ASSETS
    public static class WorldObjects {

        public static final BufferedImage NORMAL_CHEST_CLOSED = loadSingleImage("/assets/objects/nChestClosed.png");
        public static final BufferedImage NORMAL_CHEST_OPENED = loadSingleImage("/assets/objects/nChestOpened.png");

        public static final BufferedImage BUSH = loadSingleImage("/assets/objects/bush.png");
        public static final BufferedImage CANDLE_BUSH = loadSingleImage("/assets/objects/candleBush.png");

        public static final BufferedImage CANDLE = loadSingleImage("/assets/objects/candle.png");
        public static final BufferedImage BOOTS = loadSingleImage("/assets/objects/boots.png");
        public static final BufferedImage SWORD = loadSingleImage("/assets/objects/sword.png");
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

    public static class Enemies {

        private static final BufferedImage CLOUD = loadSingleImage("/assets/entities/enemies/cloud.png");
        public static final BufferedImage DEATH = loadSingleImage("/assets/entities/enemies/death.png");

        public static final BufferedImage CLOUD_1 = CLOUD != null ?
                CLOUD.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
                null;
        public static final BufferedImage CLOUD_2 = CLOUD != null ?
                CLOUD.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
                null;
        public static final BufferedImage CLOUD_3 = CLOUD != null ?
                CLOUD.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
                null;

        public static class Slime {

            public static final BufferedImage SLIME_UP = loadSingleImage("/assets/entities/enemies/slime/slimeUp.png");
            public static final BufferedImage SLIME_UP_ATTACK = loadSingleImage("/assets/entities/enemies/slime/slimeUpAttack.png");
            public static final BufferedImage SLIME_UP_DAMAGED = loadSingleImage("/assets/entities/enemies/slime/slimeUpDamaged.png");

            public static final BufferedImage SLIME_UP_1 = SLIME_UP != null ?
                    SLIME_UP.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
                    null;
            public static final BufferedImage SLIME_UP_2 = SLIME_UP != null ?
                    SLIME_UP.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
                    null;

            public static final BufferedImage SLIME_DOWN = loadSingleImage("/assets/entities/enemies/slime/slimeDown.png");
            public static final BufferedImage SLIME_DOWN_ATTACK = loadSingleImage("/assets/entities/enemies/slime/slimeDownAttack.png");
            public static final BufferedImage SLIME_DOWN_DAMAGED = loadSingleImage("/assets/entities/enemies/slime/slimeDownDamaged.png");

            public static final BufferedImage SLIME_DOWN_1 = SLIME_DOWN != null ?
                    SLIME_DOWN.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
                    null;
            public static final BufferedImage SLIME_DOWN_2 = SLIME_DOWN != null ?
                    SLIME_DOWN.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
                    null;

            public static final BufferedImage SLIME_LEFT = loadSingleImage("/assets/entities/enemies/slime/slimeLeft.png");
            public static final BufferedImage SLIME_LEFT_ATTACK = loadSingleImage("/assets/entities/enemies/slime/slimeLeftAttack.png");
            public static final BufferedImage SLIME_LEFT_DAMAGED = loadSingleImage("/assets/entities/enemies/slime/slimeLeftDamaged.png");

            public static final BufferedImage SLIME_LEFT_1 = SLIME_LEFT != null ?
                    SLIME_LEFT.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
                    null;
            public static final BufferedImage SLIME_LEFT_2 = SLIME_LEFT != null ?
                    SLIME_LEFT.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
                    null;

            public static final BufferedImage SLIME_RIGHT = loadSingleImage("/assets/entities/enemies/slime/slimeRight.png");
            public static final BufferedImage SLIME_RIGHT_ATTACK = loadSingleImage("/assets/entities/enemies/slime/slimeRightAttack.png");
            public static final BufferedImage SLIME_RIGHT_DAMAGED = loadSingleImage("/assets/entities/enemies/slime/slimeRightDamaged.png");

            public static final BufferedImage SLIME_RIGHT_1 = SLIME_RIGHT != null ?
                    SLIME_RIGHT.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
                    null;
            public static final BufferedImage SLIME_RIGHT_2 = SLIME_RIGHT != null ?
                    SLIME_RIGHT.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
                    null;
        }
    }

    // MISCELLANEOUS IMAGES
    public static class Misc {

        public static final BufferedImage ICON = loadSingleImage("/assets/misc/icon.png");
    }
}


