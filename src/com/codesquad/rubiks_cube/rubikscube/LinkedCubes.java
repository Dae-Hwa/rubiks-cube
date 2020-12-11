package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.BlockPosition;
import com.codesquad.rubiks_cube.flatcube.FlatCube;

import java.util.HashMap;
import java.util.Map;

public class LinkedCubes {
    private final Map<String, LinkedCube> linkedCubes = new HashMap<>();

    public void rotateClockWise() {
        String[] temp = getLeft().getBlocksStartAt(BlockPosition.TOP_RIGHT.VALUE);

        getLeft().setBlocksStartAt(
                BlockPosition.TOP_RIGHT.VALUE,
                getBottom().getBlocksStartAt(BlockPosition.TOP_LEFT.VALUE)
        );

        getBottom().setBlocksStartAt(
                BlockPosition.TOP_LEFT.VALUE,
                getRight().getBlocksStartAt(BlockPosition.BOTTOM_LEFT.VALUE)
        );

        getRight().setBlocksStartAt(
                BlockPosition.BOTTOM_LEFT.VALUE,
                getTop().getBlocksStartAt(BlockPosition.BOTTOM_RIGHT.VALUE)
        );

        getTop().setBlocksStartAt(
                BlockPosition.BOTTOM_RIGHT.VALUE,
                temp
        );
    }

    public void rotateCounterClockWise() {
        String[] temp = getRight().getBlocksStartAt(BlockPosition.BOTTOM_LEFT.VALUE);

        getRight().setBlocksStartAt(
                BlockPosition.BOTTOM_LEFT.VALUE,
                getBottom().getBlocksStartAt(BlockPosition.TOP_LEFT.VALUE)
        );

        getBottom().setBlocksStartAt(
                BlockPosition.TOP_LEFT.VALUE,
                getLeft().getBlocksStartAt(BlockPosition.TOP_RIGHT.VALUE)
        );

        getLeft().setBlocksStartAt(
                BlockPosition.TOP_RIGHT.VALUE,
                getTop().getBlocksStartAt(BlockPosition.BOTTOM_RIGHT.VALUE)
        );

        getTop().setBlocksStartAt(
                BlockPosition.BOTTOM_RIGHT.VALUE,
                temp
        );
    }

    public LinkedCube getLeft() {
        return linkedCubes.get("LEFT");
    }

    public LinkedCubes setLeft(FlatCube flatCube, int startPosition) {
        linkedCubes.put("LEFT", new LinkedCube(flatCube, startPosition));
        return this;
    }

    public LinkedCube getRight() {
        return linkedCubes.get("RIGHT");
    }

    public LinkedCubes setRight(FlatCube flatCube, int startPosition) {
        linkedCubes.put("RIGHT", new LinkedCube(flatCube, startPosition));
        return this;
    }

    public LinkedCube getTop() {
        return linkedCubes.get("TOP");
    }

    public LinkedCubes setTop(FlatCube flatCube, int startPosition) {
        linkedCubes.put("TOP", new LinkedCube(flatCube, startPosition));
        return this;
    }

    public LinkedCube getBottom() {
        return linkedCubes.get("BOTTOM");
    }

    public LinkedCubes setBottom(FlatCube flatCube, int startPosition) {
        linkedCubes.put("BOTTOM", new LinkedCube(flatCube, startPosition));
        return this;
    }
}
