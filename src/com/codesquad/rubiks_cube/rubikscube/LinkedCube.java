package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCube;

public class LinkedCube {
    private FlatCube flatCube;
    private int startPosition;

    public LinkedCube(FlatCube flatCube, int startPosition) {
        this.flatCube = flatCube;
        this.startPosition = startPosition;
    }
}
