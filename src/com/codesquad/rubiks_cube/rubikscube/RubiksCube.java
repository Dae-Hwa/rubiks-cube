package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.flatcube.FlatCube;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RubiksCube {
    private static final int SHUFFLE_MAX_NUM = 10;
    private static final int SHUFFLE_MIN_NUM = 1;

    private RubiksCubeLayers rubiksCubeLayers;
    private RubiksCubeLayers originalRubiksCubeLayers;
    private int rotateCount;
    private boolean isEnd;

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

    public RubiksCube executeCommand(String command) {
        if (command.equals("Q")) {
            isEnd = true;

            return this;
        }

        if (command.equals("S")) {
            shuffle(ThreadLocalRandom.current().nextInt(SHUFFLE_MIN_NUM, SHUFFLE_MAX_NUM));

            return this;
        }

        rotate(command);

        return this;
    }

    public RubiksCube shuffle(int repeatCount) {
        for (int i = 0; i < repeatCount; i++) {
            RubiksCubeCommand.getRandomInstance().execute(rubiksCubeLayers);
        }

        return this;
    }

    public RubiksCube rotate(String command) {
        rotateCount++;

        RubiksCubeCommand.getInstanceBy(command)
                .execute(rubiksCubeLayers);

        return this;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isSolved() {
        return 0 < rotateCount && rubiksCubeLayers.equals(originalRubiksCubeLayers);
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
