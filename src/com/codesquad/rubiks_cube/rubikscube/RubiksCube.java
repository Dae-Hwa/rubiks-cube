package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCube;

import java.util.*;

public class RubiksCube {
    private RubiksCubeLayers rubiksCubeLayers;
    private RubiksCubeLayers originalRubiksCubeLayers;
    private int rotateCount;

    public RubiksCube(RubiksCubeLayers rubiksCubeLayers) {
        this.rubiksCubeLayers = rubiksCubeLayers;
        originalRubiksCubeLayers = RubiksCubeLayers.create(toDTO().getRubiksCubeLayersDTO());
    }

    public static RubiksCube create() {
        FlatCube top = FlatCube.create("B");

        FlatCube bottom = FlatCube.create("R");

        List<FlatCube> middles = new ArrayList<>();

        middles.add(FlatCube.create("W"));
        middles.add(FlatCube.create("O"));
        middles.add(FlatCube.create("G"));
        middles.add(FlatCube.create("Y"));

        return new RubiksCube(new RubiksCubeLayers(top, bottom, middles));
    }

    public RubiksCube shuffle(int repeatCount) {
        for (int i = 0; i < repeatCount; i++) {
            RubiksCubeCommand.getRandomInstance().rotate(rubiksCubeLayers);
        }

        return this;
    }

    public RubiksCube rotate(String command) {
        rotateCount++;

        RubiksCubeCommand.getInstanceBy(command)
                .rotate(rubiksCubeLayers);

        return this;
    }

    public boolean isSolved() {
        return rubiksCubeLayers.equals(originalRubiksCubeLayers);
    }

    public RubiksCubeDTO toDTO() {
        return new RubiksCubeDTO(rubiksCubeLayers.toDTO(), rotateCount);
    }

    @Override
    public String toString() {
        return "RubiksCube{" +
                "rubiksCubeLayers=" + rubiksCubeLayers +
                ", rotateCount=" + rotateCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RubiksCube that = (RubiksCube) o;
        return Objects.equals(rubiksCubeLayers, that.rubiksCubeLayers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rubiksCubeLayers);
    }
}
