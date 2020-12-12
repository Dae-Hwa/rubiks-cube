package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.BlockPosition;
import com.codesquad.rubiks_cube.flatcube.FlatCube;

public class LinkedCube {
    private FlatCube flatCube;
    private int startPosition;

    public LinkedCube(FlatCube flatCube, int startPosition) {
        this.flatCube = flatCube;
        this.startPosition = startPosition;
    }

    public String[] getBlocksStartAt(int index) {
        return flatCube.getBlocksStartAt(index + startPosition);
    }

    public String[] getBlocksStartAt(BlockPosition blockPosition) {
        return getBlocksStartAt(blockPosition.VALUE);
    }

    public LinkedCube setBlocksStartAt(int index, String[] blocks) {
        flatCube.setBlocksStartAt(index + startPosition, blocks);
        return this;
    }

    public LinkedCube setBlocksStartAt(BlockPosition blockPosition, String[] blocks) {
        return setBlocksStartAt(blockPosition.VALUE, blocks);
    }
}
