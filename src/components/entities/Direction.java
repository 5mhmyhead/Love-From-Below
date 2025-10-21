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

    public double[] getVector(double magnitude) {
        return switch (this) {

            case UP -> new double[]{0, -magnitude};
            case RIGHT -> new double[]{magnitude, 0};
            case DOWN -> new double[]{0, magnitude};
            case LEFT -> new double[]{-magnitude, 0};
        };
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
