package com.codesquad.rubiks_cube.wordpusher;

public enum Direction {
    LEFT, RIGHT;

    public static Direction getInstanceBy(String direction, int repeatCount) {
        if (repeatCount < 0) {
            return getInstanceBy(direction).getReversed();
        }

        return getInstanceBy(direction);
    }

    private static Direction getInstanceBy(String direction) {
        if (direction.toUpperCase().equals("L")) {
            return LEFT;
        }

        return RIGHT;
    }

    private Direction getReversed() {
        if (this.equals(LEFT)) {
            return RIGHT;
        }

        return LEFT;
    }
}
