package utilities;

import java.awt.image.BufferedImage;

// ENUM THAT HOUSES ALL TILE DATA OF THE GAME
public enum Tile {

    EMPTY,

    ORANGE_GROUND,
    ORANGE_GRASS,
    ORANGE_FLOWERS,
    ORANGE_ROCK,
    ORANGE_BRICK,

    SMALL_TREE;

    public static Tile parseID(int id) {

        return switch(id) {
            
            case 10 -> ORANGE_GROUND;
            case 11 -> ORANGE_GRASS;
            case 12 -> ORANGE_FLOWERS;
            case 13 -> ORANGE_ROCK;
            case 14 -> ORANGE_BRICK;

            case 15 -> SMALL_TREE;

            default -> null;
        };
    }

    public int toID() {

        return switch(this) {

            case ORANGE_GROUND -> 10;
            case ORANGE_GRASS -> 11;
            case ORANGE_FLOWERS -> 12;
            case ORANGE_ROCK -> 13;
            case ORANGE_BRICK -> 14;

            case SMALL_TREE -> 15;

            default -> 0;
        };
    }

    public static BufferedImage getSprite(Tile tile) {

        return switch(tile) {

            case ORANGE_GROUND -> Images.Blocks.Orange.GROUND;
            case ORANGE_GRASS -> Images.Blocks.Orange.GRASS;
            case ORANGE_FLOWERS -> Images.Blocks.Orange.FLOWERS;
            case ORANGE_ROCK -> Images.Blocks.Orange.ROCK;
            case ORANGE_BRICK -> Images.Blocks.Orange.BRICK;

            case SMALL_TREE -> Images.Blocks.Tree.SMALL_TREE;

            default -> null;
        };
    }

    public boolean hasCollision() {

        return switch(this) {

            case SMALL_TREE -> true;

            default -> false;
        };
    }
}
