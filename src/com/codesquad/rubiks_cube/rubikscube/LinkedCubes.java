package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCube;

import java.util.HashMap;
import java.util.Map;

import static com.codesquad.rubiks_cube.flatcube.BlockPosition.*;

public class LinkedCubes {
    private final Map<LinkedCubePosition, LinkedCube> linkedCubes = new HashMap<>();

    public void rotateClockWise() {
        String[] temp = getLeft().getBlocksStartAt(TOP_RIGHT);

        getLeft().setBlocksStartAt(TOP_RIGHT, getBottom().getBlocksStartAt(TOP_LEFT));
        getBottom().setBlocksStartAt(TOP_LEFT, getRight().getBlocksStartAt(BOTTOM_LEFT));
        getRight().setBlocksStartAt(BOTTOM_LEFT, getTop().getBlocksStartAt(BOTTOM_RIGHT));
        getTop().setBlocksStartAt(BOTTOM_RIGHT, temp);
    }

    public void rotateCounterClockWise() {
        String[] temp = getRight().getBlocksStartAt(BOTTOM_LEFT);

        getRight().setBlocksStartAt(BOTTOM_LEFT, getBottom().getBlocksStartAt(TOP_LEFT));
        getBottom().setBlocksStartAt(TOP_LEFT, getLeft().getBlocksStartAt(TOP_RIGHT));
        getLeft().setBlocksStartAt(TOP_RIGHT, getTop().getBlocksStartAt(BOTTOM_RIGHT));
        getTop().setBlocksStartAt(BOTTOM_RIGHT, temp);
    }

    public LinkedCube getLeft() {
        return linkedCubes.get(LinkedCubePosition.LEFT);
    }

    public LinkedCubes setLeft(FlatCube flatCube, int startPosition) {
        linkedCubes.put(LinkedCubePosition.LEFT, new LinkedCube(flatCube, startPosition));
        return this;
    }

    public LinkedCube getRight() {
        return linkedCubes.get(LinkedCubePosition.RIGHT);
    }

    public LinkedCubes setRight(FlatCube flatCube, int startPosition) {
        linkedCubes.put(LinkedCubePosition.RIGHT, new LinkedCube(flatCube, startPosition));
        return this;
    }

    public LinkedCube getTop() {
        return linkedCubes.get(LinkedCubePosition.TOP);
    }

    public LinkedCubes setTop(FlatCube flatCube, int startPosition) {
        linkedCubes.put(LinkedCubePosition.TOP, new LinkedCube(flatCube, startPosition));
        return this;
    }

    public LinkedCube getBottom() {
        return linkedCubes.get(LinkedCubePosition.BOTTOM);
    }

    public LinkedCubes setBottom(FlatCube flatCube, int startPosition) {
        linkedCubes.put(LinkedCubePosition.BOTTOM, new LinkedCube(flatCube, startPosition));
        return this;
    }
}
