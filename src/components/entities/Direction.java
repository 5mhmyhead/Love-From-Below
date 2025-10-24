package components.entities;

public enum Direction {

    UP, RIGHT, DOWN, LEFT;

    public int getInteger() {

        return switch (this) {

            case UP -> 0;
            case RIGHT -> 1;
            case DOWN -> 2;
            case LEFT -> 3;
        };
    }

    public Direction getOpposite() {

        return switch (this) {

            case UP -> DOWN;
            case RIGHT -> LEFT;
            case DOWN -> UP;
            case LEFT -> RIGHT;
        };
    }

    public double getRadians() {

        return switch (this) {

            case UP -> 0;
            case RIGHT -> Math.PI / 2;
            case DOWN -> Math.PI;
            case LEFT -> Math.PI * 1.5;
        };
    }

    public int[] getVector(int magnitude) {

        return switch (this) {

            case UP -> new int[]{0, -magnitude};
            case RIGHT -> new int[]{magnitude, 0};
            case DOWN -> new int[]{0, magnitude};
            case LEFT -> new int[]{-magnitude, 0};
        };
    }

    // GETS A RANDOM DIRECTION FOR ENEMY MOVEMENT
    public static Direction getRandom() {

        double number = Math.random() * 4;

        if(number < 1) return UP;
        else if(number < 2) return RIGHT;
        else if(number < 3) return DOWN;
        else if(number < 4) return LEFT;
        else return DOWN;
    }

    // GETS A RANDOM DIRECTION WITH AN EXCLUDED SPECIFIED DIRECTION
    public static Direction getExcludedRandom(Direction direction) {

        Direction chosenDirection = null;

        double number = Math.random() * 4;
        if(number < 1) chosenDirection = UP;
        else if(number < 2) chosenDirection = RIGHT;
        else if(number < 3) chosenDirection = DOWN;
        else if(number < 4) chosenDirection = LEFT;
        else chosenDirection = DOWN;

        // IF IT IS THE CHOSEN DIRECTION, RUN THE FUNCTION AGAIN UNTIL WE GET A DIRECTION THAT WAS NOT PROVIDED
        if(chosenDirection == direction) chosenDirection = getExcludedRandom(direction);
        return chosenDirection;
    }

    public static Direction parseString(String direction) {

        return switch (direction) {

            case "UP" -> UP;
            case "RIGHT" -> RIGHT;
            case "DOWN" -> DOWN;
            case "LEFT" -> LEFT;

            default -> null;
        };
    }
}
