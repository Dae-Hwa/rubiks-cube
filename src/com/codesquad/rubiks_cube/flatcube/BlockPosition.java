package com.codesquad.rubiks_cube.flatcube;

public enum BlockPosition {
    TOP_LEFT(0), TOP_RIGHT(2), BOTTOM_LEFT(6), BOTTOM_RIGHT(4);

    public final int VALUE;

    BlockPosition(int value) {
        this.VALUE = value;
    }
}
