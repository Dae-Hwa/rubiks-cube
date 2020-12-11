package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCube;

public class LinkedCube {
    private FlatCube flatCube;
    private int startPosition;

    public LinkedCube(FlatCube flatCube, int startPosition) {
        this.flatCube = flatCube;
        this.startPosition = startPosition;
    }

    // 여기서 블록 집어넣기
    public String[] getBlocksStartAt(int index) {
        return flatCube.getBlocksStartAt(index + startPosition);
    }

    public LinkedCube setBlocksStartAt(int index, String[] blocks) {
        flatCube.setBlocksStartAt(index + startPosition, blocks);
        return this;
    }
}
