package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCube;

import java.util.HashMap;
import java.util.Map;

public class LinkedCubes {
    private final Map<String, LinkedCube> linkedCubes = new HashMap<>();

    public LinkedCubes setLeft(FlatCube flatCube, int startPosition) {
        linkedCubes.put("LEFT", new LinkedCube(flatCube, startPosition));
        return this;
    }

    public LinkedCubes setRight(FlatCube flatCube, int startPosition) {
        linkedCubes.put("RIGHT", new LinkedCube(flatCube, startPosition));
        return this;
    }

    public LinkedCubes setTop(FlatCube flatCube, int startPosition) {
        linkedCubes.put("TOP", new LinkedCube(flatCube, startPosition));
        return this;
    }

    public LinkedCubes setBottom(FlatCube flatCube, int startPosition) {
        linkedCubes.put("BOTTOM", new LinkedCube(flatCube, startPosition));
        return this;
    }
}
