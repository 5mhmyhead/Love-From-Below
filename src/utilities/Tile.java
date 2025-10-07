package utilities;

import java.awt.image.BufferedImage;

// ENUM THAT HOUSES ALL TILE DATA OF THE GAME
public enum Tile {

    SOLID_1,SOLID_2, SOLID_3, SOLID_4,

    GRASS_1, GRASS_2, GRASS_3, GRASS_4, GRASS_5, GRASS_6, GRASS_7, GRASS_8, GRASS_9, GRASS_10,
    GRASS_11, GRASS_12, GRASS_13, GRASS_14, GRASS_15, GRASS_16, GRASS_17, GRASS_18, GRASS_19, GRASS_20,
    GRASS_21, GRASS_22, GRASS_23, GRASS_24, GRASS_25, GRASS_26, GRASS_27, GRASS_28, GRASS_29, GRASS_30,
    GRASS_31, GRASS_32, GRASS_33, GRASS_34, GRASS_35, GRASS_36, GRASS_37, GRASS_38, GRASS_39, GRASS_40,
    GRASS_41, GRASS_42, GRASS_43, GRASS_44, GRASS_45, GRASS_46, GRASS_47,

    CAVE_WALLS_1, CAVE_WALLS_2, CAVE_WALLS_3, CAVE_WALLS_4, CAVE_WALLS_5,
    CAVE_WALLS_6, CAVE_WALLS_7, CAVE_WALLS_8, CAVE_WALLS_9, CAVE_WALLS_10,
    CAVE_WALLS_11, CAVE_WALLS_12, CAVE_WALLS_13, CAVE_WALLS_14, CAVE_WALLS_15,
    CAVE_WALLS_16, CAVE_WALLS_17, CAVE_WALLS_18, CAVE_WALLS_19, CAVE_WALLS_20,
    CAVE_WALLS_21, CAVE_WALLS_22, CAVE_WALLS_23, CAVE_WALLS_24, CAVE_WALLS_25,
    CAVE_WALLS_26, CAVE_WALLS_27, CAVE_WALLS_28, CAVE_WALLS_29, CAVE_WALLS_30,
    CAVE_WALLS_31, CAVE_WALLS_32, CAVE_WALLS_33, CAVE_WALLS_34, CAVE_WALLS_35;

    public static Tile parseTileID(int id) {

        return switch(id) {

            case 10 -> SOLID_1;
            case 11 -> SOLID_2;
            case 12 -> SOLID_3;
            case 13 -> SOLID_4;

            case 14 -> GRASS_1;
            case 15 -> GRASS_2;
            case 16 -> GRASS_3;
            case 17 -> GRASS_4;
            case 18 -> GRASS_5;
            case 19 -> GRASS_6;
            case 20 -> GRASS_7;
            case 21 -> GRASS_8;
            case 22 -> GRASS_9;
            case 23 -> GRASS_10;
            case 24 -> GRASS_11;
            case 25 -> GRASS_12;
            case 26 -> GRASS_13;
            case 27 -> GRASS_14;
            case 28 -> GRASS_15;
            case 29 -> GRASS_16;
            case 30 -> GRASS_17;
            case 31 -> GRASS_18;
            case 32 -> GRASS_19;
            case 33 -> GRASS_20;
            case 34 -> GRASS_21;
            case 35 -> GRASS_22;
            case 36 -> GRASS_23;
            case 37 -> GRASS_24;
            case 38 -> GRASS_25;
            case 39 -> GRASS_26;
            case 40 -> GRASS_27;
            case 41 -> GRASS_28;
            case 42 -> GRASS_29;
            case 43 -> GRASS_30;
            case 44 -> GRASS_31;
            case 45 -> GRASS_32;
            case 46 -> GRASS_33;
            case 47 -> GRASS_34;
            case 48 -> GRASS_35;
            case 49 -> GRASS_36;
            case 50 -> GRASS_37;
            case 51 -> GRASS_38;
            case 52 -> GRASS_39;
            case 53 -> GRASS_40;
            case 54 -> GRASS_41;
            case 55 -> GRASS_42;
            case 56 -> GRASS_43;
            case 57 -> GRASS_44;
            case 58 -> GRASS_45;
            case 59 -> GRASS_46;
            case 60 -> GRASS_47;

            case 61 -> CAVE_WALLS_1;
            case 62 -> CAVE_WALLS_2;
            case 63 -> CAVE_WALLS_3;
            case 64 -> CAVE_WALLS_4;
            case 65 -> CAVE_WALLS_5;
            case 66 -> CAVE_WALLS_6;
            case 67 -> CAVE_WALLS_7;
            case 68 -> CAVE_WALLS_8;
            case 69 -> CAVE_WALLS_9;
            case 70 -> CAVE_WALLS_10;
            case 71 -> CAVE_WALLS_11;
            case 72 -> CAVE_WALLS_12;
            case 73 -> CAVE_WALLS_13;
            case 74 -> CAVE_WALLS_14;
            case 75 -> CAVE_WALLS_15;
            case 76 -> CAVE_WALLS_16;
            case 77 -> CAVE_WALLS_17;
            case 78 -> CAVE_WALLS_18;
            case 79 -> CAVE_WALLS_19;
            case 80 -> CAVE_WALLS_20;
            case 81 -> CAVE_WALLS_21;
            case 82 -> CAVE_WALLS_22;
            case 83 -> CAVE_WALLS_23;
            case 84 -> CAVE_WALLS_24;
            case 85 -> CAVE_WALLS_25;
            case 86 -> CAVE_WALLS_26;
            case 87 -> CAVE_WALLS_27;
            case 88 -> CAVE_WALLS_28;
            case 89 -> CAVE_WALLS_29;
            case 90 -> CAVE_WALLS_30;
            case 91 -> CAVE_WALLS_31;
            case 92 -> CAVE_WALLS_32;
            case 93 -> CAVE_WALLS_33;
            case 94 -> CAVE_WALLS_34;
            case 95 -> CAVE_WALLS_35;

            default -> null;
        };
    }

    public int toTileID() {

        return switch(this) {

            case SOLID_1 -> 10;
            case SOLID_2 -> 11;
            case SOLID_3 -> 12;
            case SOLID_4 -> 13;

            case GRASS_1 -> 14;
            case GRASS_2 -> 15;
            case GRASS_3 -> 16;
            case GRASS_4 -> 17;
            case GRASS_5 -> 18;
            case GRASS_6 -> 19;
            case GRASS_7 -> 20;
            case GRASS_8 -> 21;
            case GRASS_9 -> 22;
            case GRASS_10 -> 23;
            case GRASS_11 -> 24;
            case GRASS_12 -> 25;
            case GRASS_13 -> 26;
            case GRASS_14 -> 27;
            case GRASS_15 -> 28;
            case GRASS_16 -> 29;
            case GRASS_17 -> 30;
            case GRASS_18 -> 31;
            case GRASS_19 -> 32;
            case GRASS_20 -> 33;
            case GRASS_21 -> 34;
            case GRASS_22 -> 35;
            case GRASS_23 -> 36;
            case GRASS_24 -> 37;
            case GRASS_25 -> 38;
            case GRASS_26 -> 39;
            case GRASS_27 -> 40;
            case GRASS_28 -> 41;
            case GRASS_29 -> 42;
            case GRASS_30 -> 43;
            case GRASS_31 -> 44;
            case GRASS_32 -> 45;
            case GRASS_33 -> 46;
            case GRASS_34 -> 47;
            case GRASS_35 -> 48;
            case GRASS_36 -> 49;
            case GRASS_37 -> 50;
            case GRASS_38 -> 51;
            case GRASS_39 -> 52;
            case GRASS_40 -> 53;
            case GRASS_41 -> 54;
            case GRASS_42 -> 55;
            case GRASS_43 -> 56;
            case GRASS_44 -> 57;
            case GRASS_45 -> 58;
            case GRASS_46 -> 59;
            case GRASS_47 -> 60;

            case CAVE_WALLS_1 -> 61;
            case CAVE_WALLS_2 -> 62;
            case CAVE_WALLS_3 -> 63;
            case CAVE_WALLS_4 -> 64;
            case CAVE_WALLS_5 -> 65;
            case CAVE_WALLS_6 -> 66;
            case CAVE_WALLS_7 -> 67;
            case CAVE_WALLS_8 -> 68;
            case CAVE_WALLS_9 -> 69;
            case CAVE_WALLS_10 -> 70;
            case CAVE_WALLS_11 -> 71;
            case CAVE_WALLS_12 -> 72;
            case CAVE_WALLS_13 -> 73;
            case CAVE_WALLS_14 -> 74;
            case CAVE_WALLS_15 -> 75;
            case CAVE_WALLS_16 -> 76;
            case CAVE_WALLS_17 -> 77;
            case CAVE_WALLS_18 -> 78;
            case CAVE_WALLS_19 -> 79;
            case CAVE_WALLS_20 -> 80;
            case CAVE_WALLS_21 -> 81;
            case CAVE_WALLS_22 -> 82;
            case CAVE_WALLS_23 -> 83;
            case CAVE_WALLS_24 -> 84;
            case CAVE_WALLS_25 -> 85;
            case CAVE_WALLS_26 -> 86;
            case CAVE_WALLS_27 -> 87;
            case CAVE_WALLS_28 -> 88;
            case CAVE_WALLS_29 -> 89;
            case CAVE_WALLS_30 -> 90;
            case CAVE_WALLS_31 -> 91;
            case CAVE_WALLS_32 -> 92;
            case CAVE_WALLS_33 -> 93;
            case CAVE_WALLS_34 -> 94;
            case CAVE_WALLS_35 -> 95;
        };
    }

    public static BufferedImage getTileSprite(Tile tile) {

        return switch(tile) {

            case SOLID_1 -> Images.Tiles.Solid.SOLID_1;
            case SOLID_2 -> Images.Tiles.Solid.SOLID_2;
            case SOLID_3 -> Images.Tiles.Solid.SOLID_3;
            case SOLID_4 -> Images.Tiles.Solid.SOLID_4;

            case GRASS_1 -> Images.Tiles.Ground.Grass.GRASS_1;
            case GRASS_2 -> Images.Tiles.Ground.Grass.GRASS_2;
            case GRASS_3 -> Images.Tiles.Ground.Grass.GRASS_3;
            case GRASS_4 -> Images.Tiles.Ground.Grass.GRASS_4;
            case GRASS_5 -> Images.Tiles.Ground.Grass.GRASS_5;
            case GRASS_6 -> Images.Tiles.Ground.Grass.GRASS_6;
            case GRASS_7 -> Images.Tiles.Ground.Grass.GRASS_7;
            case GRASS_8 -> Images.Tiles.Ground.Grass.GRASS_8;
            case GRASS_9 -> Images.Tiles.Ground.Grass.GRASS_9;
            case GRASS_10 -> Images.Tiles.Ground.Grass.GRASS_10;
            case GRASS_11 -> Images.Tiles.Ground.Grass.GRASS_11;
            case GRASS_12 -> Images.Tiles.Ground.Grass.GRASS_12;
            case GRASS_13 -> Images.Tiles.Ground.Grass.GRASS_13;
            case GRASS_14 -> Images.Tiles.Ground.Grass.GRASS_14;
            case GRASS_15 -> Images.Tiles.Ground.Grass.GRASS_15;
            case GRASS_16 -> Images.Tiles.Ground.Grass.GRASS_16;
            case GRASS_17 -> Images.Tiles.Ground.Grass.GRASS_17;
            case GRASS_18 -> Images.Tiles.Ground.Grass.GRASS_18;
            case GRASS_19 -> Images.Tiles.Ground.Grass.GRASS_19;
            case GRASS_20 -> Images.Tiles.Ground.Grass.GRASS_20;
            case GRASS_21 -> Images.Tiles.Ground.Grass.GRASS_21;
            case GRASS_22 -> Images.Tiles.Ground.Grass.GRASS_22;
            case GRASS_23 -> Images.Tiles.Ground.Grass.GRASS_23;
            case GRASS_24 -> Images.Tiles.Ground.Grass.GRASS_24;
            case GRASS_25 -> Images.Tiles.Ground.Grass.GRASS_25;
            case GRASS_26 -> Images.Tiles.Ground.Grass.GRASS_26;
            case GRASS_27 -> Images.Tiles.Ground.Grass.GRASS_27;
            case GRASS_28 -> Images.Tiles.Ground.Grass.GRASS_28;
            case GRASS_29 -> Images.Tiles.Ground.Grass.GRASS_29;
            case GRASS_30 -> Images.Tiles.Ground.Grass.GRASS_30;
            case GRASS_31 -> Images.Tiles.Ground.Grass.GRASS_31;
            case GRASS_32 -> Images.Tiles.Ground.Grass.GRASS_32;
            case GRASS_33 -> Images.Tiles.Ground.Grass.GRASS_33;
            case GRASS_34 -> Images.Tiles.Ground.Grass.GRASS_34;
            case GRASS_35 -> Images.Tiles.Ground.Grass.GRASS_35;
            case GRASS_36 -> Images.Tiles.Ground.Grass.GRASS_36;
            case GRASS_37 -> Images.Tiles.Ground.Grass.GRASS_37;
            case GRASS_38 -> Images.Tiles.Ground.Grass.GRASS_38;
            case GRASS_39 -> Images.Tiles.Ground.Grass.GRASS_39;
            case GRASS_40 -> Images.Tiles.Ground.Grass.GRASS_40;
            case GRASS_41 -> Images.Tiles.Ground.Grass.GRASS_41;
            case GRASS_42 -> Images.Tiles.Ground.Grass.GRASS_42;
            case GRASS_43 -> Images.Tiles.Ground.Grass.GRASS_43;
            case GRASS_44 -> Images.Tiles.Ground.Grass.GRASS_44;
            case GRASS_45 -> Images.Tiles.Ground.Grass.GRASS_45;
            case GRASS_46 -> Images.Tiles.Ground.Grass.GRASS_46;
            case GRASS_47 -> Images.Tiles.Ground.Grass.GRASS_47;

            case CAVE_WALLS_1 -> Images.Tiles.Walls.Cave.CAVE_WALL_1;
            case CAVE_WALLS_2 -> Images.Tiles.Walls.Cave.CAVE_WALL_2;
            case CAVE_WALLS_3 -> Images.Tiles.Walls.Cave.CAVE_WALL_3;
            case CAVE_WALLS_4 -> Images.Tiles.Walls.Cave.CAVE_WALL_4;
            case CAVE_WALLS_5 -> Images.Tiles.Walls.Cave.CAVE_WALL_5;
            case CAVE_WALLS_6 -> Images.Tiles.Walls.Cave.CAVE_WALL_6;
            case CAVE_WALLS_7 -> Images.Tiles.Walls.Cave.CAVE_WALL_7;
            case CAVE_WALLS_8 -> Images.Tiles.Walls.Cave.CAVE_WALL_8;
            case CAVE_WALLS_9 -> Images.Tiles.Walls.Cave.CAVE_WALL_9;
            case CAVE_WALLS_10 -> Images.Tiles.Walls.Cave.CAVE_WALL_10;
            case CAVE_WALLS_11 -> Images.Tiles.Walls.Cave.CAVE_WALL_11;
            case CAVE_WALLS_12 -> Images.Tiles.Walls.Cave.CAVE_WALL_12;
            case CAVE_WALLS_13 -> Images.Tiles.Walls.Cave.CAVE_WALL_13;
            case CAVE_WALLS_14 -> Images.Tiles.Walls.Cave.CAVE_WALL_14;
            case CAVE_WALLS_15 -> Images.Tiles.Walls.Cave.CAVE_WALL_15;
            case CAVE_WALLS_16 -> Images.Tiles.Walls.Cave.CAVE_WALL_16;
            case CAVE_WALLS_17 -> Images.Tiles.Walls.Cave.CAVE_WALL_17;
            case CAVE_WALLS_18 -> Images.Tiles.Walls.Cave.CAVE_WALL_18;
            case CAVE_WALLS_19 -> Images.Tiles.Walls.Cave.CAVE_WALL_19;
            case CAVE_WALLS_20 -> Images.Tiles.Walls.Cave.CAVE_WALL_20;
            case CAVE_WALLS_21 -> Images.Tiles.Walls.Cave.CAVE_WALL_21;
            case CAVE_WALLS_22 -> Images.Tiles.Walls.Cave.CAVE_WALL_22;
            case CAVE_WALLS_23 -> Images.Tiles.Walls.Cave.CAVE_WALL_23;
            case CAVE_WALLS_24 -> Images.Tiles.Walls.Cave.CAVE_WALL_24;
            case CAVE_WALLS_25 -> Images.Tiles.Walls.Cave.CAVE_WALL_25;
            case CAVE_WALLS_26 -> Images.Tiles.Walls.Cave.CAVE_WALL_26;
            case CAVE_WALLS_27 -> Images.Tiles.Walls.Cave.CAVE_WALL_27;
            case CAVE_WALLS_28 -> Images.Tiles.Walls.Cave.CAVE_WALL_28;
            case CAVE_WALLS_29 -> Images.Tiles.Walls.Cave.CAVE_WALL_29;
            case CAVE_WALLS_30 -> Images.Tiles.Walls.Cave.CAVE_WALL_30;
            case CAVE_WALLS_31 -> Images.Tiles.Walls.Cave.CAVE_WALL_31;
            case CAVE_WALLS_32 -> Images.Tiles.Walls.Cave.CAVE_WALL_32;
            case CAVE_WALLS_33 -> Images.Tiles.Walls.Cave.CAVE_WALL_33;
            case CAVE_WALLS_34 -> Images.Tiles.Walls.Cave.CAVE_WALL_34;
            case CAVE_WALLS_35 -> Images.Tiles.Walls.Cave.CAVE_WALL_35;

            default -> null;
        };
    }

    public boolean hasTileCollision() {

        return switch(this) {
            // ONLY THE SOLID BLACK TILE HAS COLLISION
            case SOLID_1 -> true;

            case CAVE_WALLS_1 -> true;
            case CAVE_WALLS_2 -> true;
            case CAVE_WALLS_3 -> true;
            case CAVE_WALLS_4 -> true;
            case CAVE_WALLS_5 -> true;
            case CAVE_WALLS_6 -> true;
            case CAVE_WALLS_7 -> true;
            case CAVE_WALLS_8 -> true;
            case CAVE_WALLS_9 -> true;
            case CAVE_WALLS_10 -> true;
            case CAVE_WALLS_11 -> true;
            case CAVE_WALLS_12 -> true;
            case CAVE_WALLS_13 -> true;
            case CAVE_WALLS_14 -> true;
            case CAVE_WALLS_15 -> true;
            case CAVE_WALLS_16 -> true;
            case CAVE_WALLS_17 -> true;
            case CAVE_WALLS_18 -> true;
            case CAVE_WALLS_19 -> true;
            case CAVE_WALLS_20 -> true;
            case CAVE_WALLS_21 -> true;
            case CAVE_WALLS_22 -> true;
            case CAVE_WALLS_23 -> true;
            case CAVE_WALLS_24 -> true;
            case CAVE_WALLS_25 -> true;
            case CAVE_WALLS_26 -> true;
            case CAVE_WALLS_27 -> true;
            case CAVE_WALLS_28 -> true;
            case CAVE_WALLS_29 -> true;
            case CAVE_WALLS_30 -> true;
            case CAVE_WALLS_31 -> true;
            case CAVE_WALLS_32 -> true;
            case CAVE_WALLS_33 -> true;
            case CAVE_WALLS_34 -> true;
            case CAVE_WALLS_35 -> true;

            default -> false;
        };
    }
}
